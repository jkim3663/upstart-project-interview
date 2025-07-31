package com.jkim3663.backend.service;

import com.jkim3663.backend.dto.ProductDTO;
import com.jkim3663.backend.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(e -> {
                    ProductDTO productDTO = new ProductDTO();
                    productDTO.setName(e.getName());
                    productDTO.setPrice(e.getPrice().toString());
                    productDTO.setCreatedAt(e.getCreatedAt().toString());
                    return productDTO;
                }).collect(Collectors.toList());
    }
}
