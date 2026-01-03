package com.ecommerce.api.model;

import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;
import java.util.List;

public class Product {
    private Long id;
    private String name;
    private String brand;
    private String description;
    private BigDecimal price;
    private int inventory;

    @ManyToOne
    private Category category;

    private List<Image> image;
}
