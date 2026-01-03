package com.ecommerce.api.model;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.sql.Blob;

public class Image {
    private Long id;
    private String fileName;
    private String filePath;
    private Blob image;
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
