package com.jkim3663.backend.controller;

import com.jkim3663.backend.dto.ProductRequestDTO;
import com.jkim3663.backend.dto.ProductResponseDTO;
import com.jkim3663.backend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNewProduct(@RequestBody ProductRequestDTO productRequestDTO) {
        try {
            if (!productService.isValidProduct(productRequestDTO)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            productService.addNewProduct(productRequestDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
