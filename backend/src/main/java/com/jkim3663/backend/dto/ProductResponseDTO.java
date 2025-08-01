package com.jkim3663.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDTO {
    private Integer id;
    private String name;
    private String price;
    private String createdAt;
}
