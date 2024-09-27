package com.example.SustainGifts.converter;

import com.example.SustainGifts.dtos.ProductDTO;
import com.example.SustainGifts.models.ProductEntity;
import com.example.SustainGifts.repositories.CategoryRepository;
import com.example.SustainGifts.repositories.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ProductConverter {

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * Converts a ProductEntity entity to a CategoryDTO.
     *
     * @param productEntity the ProductEntity entity to convert
     * @return the converted ProductDTO
     */
    public ProductDTO convertToDTO(ProductEntity productEntity) {
        return ProductDTO.builder()
                .id(productEntity.getId())
                .title(productEntity.getTitle())
                .alias(productEntity.getAlias())
                .description(productEntity.getDescription())
                .price(productEntity.getPrice())
                .imageURL(productEntity.getImageURL())
                .vendorId(productEntity.getVendorEntity().getId())
                .vendorTitle(productEntity.getVendorEntity().getTitle())
                .categoryId(productEntity.getCategoryEntity().getId())
                .categoryTitle(productEntity.getCategoryEntity().getTitle())
                .build();
    }

    /**
     * Converts a ProductDTO to a ProductEntity entity.
     *
     * @param productDTO the ProductDTO to convert
     * @return the converted ProductEntity entity
     */
    public ProductEntity convertToEntity(ProductDTO productDTO) {
        return ProductEntity.builder()
                .id(productDTO.getId())
                .title(productDTO.getTitle())
                .alias(productDTO.getAlias())
                .description(productDTO.getDescription())
                .price(productDTO.getPrice())
                .imageURL(productDTO.getImageURL())
                .vendorEntity(vendorRepository.findById(productDTO.getVendorId()).orElse(null)) // Convert vendorEntity
                .categoryEntity(categoryRepository.findById(productDTO.getCategoryId()).orElse(null)) // Convert categoryEntity
                .build();
    }

    /**
     * Converts a set of child ProductEntity entities to a set of ProductDTOs.
     *
     * @param children the set of child ProductEntity entities
     * @return the set of converted ProductDTOs
     */
    private Set<ProductDTO> convertChildrenToDTO(Set<ProductEntity> children) {
        return children.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toSet());
    }
}
