package com.ecommerce.api.data;

import com.ecommerce.api.model.Category;
import com.ecommerce.api.model.Image;
import com.ecommerce.api.model.Product;
import com.ecommerce.api.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        if (productRepository.count() == 0) {
            seedData();
        }
    }

    private void seedData() {
        Category electronics = new Category("Electronics");
        Category fashion = new Category("Fashion");
        Category home = new Category("Home & Kitchen");
        Category beauty = new Category("Beauty");

        Product p1 = new Product("Smartphone X", "TechBrand", new BigDecimal("699.99"), 50,
                "Latest smartphone with high res camera", electronics);
        p1.setImages(createImages(p1, "smartphone-x", "https://via.placeholder.com/150"));

        Product p2 = new Product("Laptop Pro", "TechBrand", new BigDecimal("1299.99"), 30,
                "Powerful laptop for professionals", electronics);
        p2.setImages(createImages(p2, "laptop-pro", "https://via.placeholder.com/150"));

        Product p3 = new Product("Running Shoes", "Sporty", new BigDecimal("89.99"), 100, "Comfortable running shoes",
                fashion);
        p3.setImages(createImages(p3, "running-shoes", "https://via.placeholder.com/150"));

        Product p4 = new Product("Jeans", "DenimCo", new BigDecimal("49.99"), 200, "Classic blue jeans", fashion);
        p4.setImages(createImages(p4, "jeans", "https://via.placeholder.com/150"));

        Product p5 = new Product("Blender", "MixIt", new BigDecimal("39.99"), 75, "High speed blender", home);
        p5.setImages(createImages(p5, "blender", "https://via.placeholder.com/150"));

        Product p6 = new Product("Face Cream", "Glow", new BigDecimal("25.00"), 150, "Hydrating face cream", beauty);
        p6.setImages(createImages(p6, "face-cream", "https://via.placeholder.com/150"));

        productRepository.saveAll(List.of(p1, p2, p3, p4, p5, p6));

        System.out.println("Data seeded successfully!");
    }

    private List<Image> createImages(Product product, String nameSlug, String url) {
        Image image1 = new Image();
        image1.setFileName(nameSlug + "-1.jpg");
        image1.setFileType("image/jpeg");
        image1.setImageUrl(url);
        image1.setProduct(product);

        Image image2 = new Image();
        image2.setFileName(nameSlug + "-2.jpg");
        image2.setFileType("image/jpeg");
        image2.setImageUrl(url);
        image2.setProduct(product);

        return List.of(image1, image2);
    }
}
