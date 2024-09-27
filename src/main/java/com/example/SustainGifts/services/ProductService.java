package com.example.SustainGifts.services;

import com.example.SustainGifts.converter.ProductConverter;
import com.example.SustainGifts.dtos.ProductDTO;
import com.example.SustainGifts.models.ProductEntity;
import com.example.SustainGifts.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductConverter productConverter;

    /**
     * Retrieve all productEntities and convert them to DTOs.
     *
     * @return a list of ProductDTOs
     */
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieve a productEntity by its ID and convert it to a DTO.
     *
     * @param id the ID of the productEntity
     * @return the corresponding ProductDTO
     * @throws RuntimeException if no productEntity is found with the given ID
     */
    public ProductDTO getProductById(Integer id) {
        return productRepository.findById(id)
                .map(productConverter::convertToDTO)
                .orElseThrow(() -> new RuntimeException("ProductEntity not found with ID: " + id));
    }

    /**
     * Save a new productEntity by converting a ProductDTO to an entity.
     *
     * @param productDTO the data transfer object for the new productEntity
     * @return the saved ProductDTO
     */
    public ProductDTO saveProduct(ProductDTO productDTO) {
        ProductEntity productEntity = productConverter.convertToEntity(productDTO);
        ProductEntity savedProductEntity = productRepository.save(productEntity);
        return productConverter.convertToDTO(savedProductEntity);
    }

    /**
     * Update an existing productEntity's details.
     *
     * @param id          the ID of the productEntity to update
     * @param productDTO  the updated ProductDTO
     * @return the updated ProductDTO
     * @throws RuntimeException if no productEntity is found with the given ID
     */
    public ProductDTO updateProduct(Integer id, ProductDTO productDTO) {
        ProductEntity existingProductEntity = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProductEntity not found with ID: " + id));

        updateProductDetails(existingProductEntity, productDTO);
        ProductEntity updatedProductEntity = productRepository.save(existingProductEntity);
        return productConverter.convertToDTO(updatedProductEntity);
    }

    /**
     * Helper method to update productEntity details from a ProductDTO.
     *
     * @param productEntity     the productEntity entity to update
     * @param productDTO  the source DTO containing updated details
     */
    private void updateProductDetails(ProductEntity productEntity, ProductDTO productDTO) {
        productEntity.setTitle(productDTO.getTitle());
        productEntity.setAlias(productDTO.getAlias());
        productEntity.setDescription(productDTO.getDescription());
        productEntity.setPrice(productDTO.getPrice());
        productEntity.setImageURL(productDTO.getImageURL());
        // I Can add conversion for vendorEntity and categoryEntity if needed HHHHHS
    }

    /**
     * Delete a productEntity by its ID.
     *
     * @param id the ID of the productEntity to delete
     * @return true if the productEntity was deleted, false if not found
     */
    public boolean deleteProduct(Integer id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

}