package com.ecommerce.api.service.image;

import com.ecommerce.api.dto.ImageDto;
import com.ecommerce.api.exceptions.ResourceNotFoundException;
import com.ecommerce.api.model.Image;
import com.ecommerce.api.model.Product;
import com.ecommerce.api.repository.ImageRepository;
import com.ecommerce.api.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService implements IImageService {
    private final ImageRepository imageRepository;
    private final ProductService productService;

    @Override
    public Image getImageById(Long id) {
        return imageRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No image was found with id: " + id));
    }

    @Override
    public void deleteImageById(Long id) {
        imageRepository.findById(id).ifPresentOrElse(imageRepository::delete, () -> {
            throw new ResourceNotFoundException("No image was found with id: " + id);
        });
    }

    @Override
    public List<ImageDto> saveImages(List<MultipartFile> files, Long id) {
        Product product = productService.getProductById(id);
        List<ImageDto> savedImageDtos = new ArrayList<>();
        for  (MultipartFile file : files) {
            try {
                Image image = new Image();
                image.setFileName(file.getOriginalFilename());
                image.setFileType(file.getContentType());
                image.setImage(new SerialBlob(file.getBytes()));
                image.setProduct(product);

                String downloadUrl = "/api/v1/images/image/download/";
                String imageUrl = downloadUrl + image.getId();

                image.setImageUrl(imageUrl);
                Image savedImage = imageRepository.save(image);

                savedImage.setImageUrl(downloadUrl + savedImage.getId());
                imageRepository.save(savedImage);

                ImageDto imageDto = new ImageDto();
                imageDto.setImageId(savedImage.getId());
                imageDto.setImageName(savedImage.getFileName());
                imageDto.setImageUrl(savedImage.getImageUrl());
                savedImageDtos.add(imageDto);
            }
            catch (SQLException | IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return savedImageDtos;
    }

    @Override
    public void updateImage(MultipartFile file, Long id) {
        Image image = getImageById(id);

        try {
            image.setFileName(file.getOriginalFilename());
            image.setFileType(file.getContentType());
            image.setImage(new SerialBlob(file.getBytes()));
            imageRepository.save(image);
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
