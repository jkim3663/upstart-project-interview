package com.jkim3663.backend.service.impl;

import com.jkim3663.backend.dto.ProductRequestDTO;
import com.jkim3663.backend.dto.ProductResponseDTO;
import com.jkim3663.backend.entity.ProductEntity;
import com.jkim3663.backend.repository.ProductRepository;
import com.jkim3663.backend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<ProductResponseDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(e -> {
                    ProductResponseDTO productResponseDTO = new ProductResponseDTO();
                    productResponseDTO.setId(e.getId());
                    productResponseDTO.setName(e.getName());
                    productResponseDTO.setPrice(e.getPrice().toString());
                    productResponseDTO.setCreatedAt(e.getCreatedAt().toString());
                    return productResponseDTO;
                }).collect(Collectors.toList());
    }

    @Override
    public boolean isValidProduct(ProductRequestDTO productRequestDTO) {
        if (productRequestDTO.getName() == null || productRequestDTO.getName().isEmpty()) {
            return false;
        }
        if (productRequestDTO.getPrice() == null || productRequestDTO.getPrice().isEmpty()) {
            return false;
        }
        try {
            // if the conversion to bigdecimal fails then it is an invalid string input\
            BigDecimal bigDecimal = new BigDecimal(productRequestDTO.getPrice());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void addNewProduct(ProductRequestDTO productRequestDTO) {
        try {
            ProductEntity productEntity = populateProductEntity(productRequestDTO);
            productRepository.save(productEntity);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private ProductEntity populateProductEntity(ProductRequestDTO productRequestDTO) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(productRequestDTO.getName());
        productEntity.setPrice(new BigDecimal(productRequestDTO.getPrice()));
        return productEntity;
    }
}
