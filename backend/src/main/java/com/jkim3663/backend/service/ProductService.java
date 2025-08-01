package com.jkim3663.backend.service;

import com.jkim3663.backend.dto.ProductRequestDTO;
import com.jkim3663.backend.dto.ProductResponseDTO;

import java.util.List;

public interface ProductService {

    List<ProductResponseDTO> getAllProducts();

    boolean isValidProduct(ProductRequestDTO productRequestDTO);

    void addNewProduct(ProductRequestDTO productRequestDTO);
}
