package com.example.SustainGifts.services;


import com.example.SustainGifts.converter.CategoryConverter;
import com.example.SustainGifts.dtos.CategoryDTO;
import com.example.SustainGifts.models.CategoryEntity;
import com.example.SustainGifts.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    private final CategoryConverter categoryConverter;

    /**
     * Retrieve all categories from the database and convert them to DTOs.
     *
     * @return a list of CategoryDTOs
     */
    public List<CategoryDTO> getAllCategories() {
        List<CategoryEntity> categories = categoryRepository.findAll();
        return categories.stream()
                .map(categoryConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieve a categoryEntity by its ID and convert it to a DTO.
     *
     * @param id the ID of the categoryEntity
     * @return the corresponding CategoryDTO
     * @throws RuntimeException if no categoryEntity is found with the given ID
     */
    public CategoryDTO getCategoryById(Integer id) {
        return categoryRepository.findById(id)
                .map(categoryConverter::convertToDTO)
                .orElseThrow(() -> new RuntimeException("CategoryEntity not found with ID: " + id));
    }

    /**
     * Create a new categoryEntity by converting a CategoryDTO to an entity and saving it.
     *
     * @param categoryDTO the data transfer object for the new categoryEntity
     * @return the saved CategoryDTO
     */
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        CategoryEntity categoryEntity = categoryConverter.convertToEntity(categoryDTO);
        CategoryEntity savedCategoryEntity = categoryRepository.save(categoryEntity);
        return categoryConverter.convertToDTO(savedCategoryEntity);
    }

    /**
     * Update an existing categoryEntity's details.
     *
     * @param id          the ID of the categoryEntity to update
     * @param categoryDTO the updated CategoryDTO
     * @return the updated CategoryDTO
     * @throws RuntimeException if the categoryEntity with the given ID is not found
     */
    public CategoryDTO updateCategory(Integer id, CategoryDTO categoryDTO) {
        CategoryEntity categoryEntity = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CategoryEntity not found with ID: " + id));

        updateCategoryDetails(categoryEntity, categoryDTO);
        return categoryConverter.convertToDTO(categoryRepository.save(categoryEntity));
    }

    /**
     * Delete a categoryEntity by its ID.
     *
     * @param id the ID of the categoryEntity to delete
     * @throws RuntimeException if no categoryEntity is found with the given ID
     */
    public void deleteCategory(Integer id) {
        CategoryEntity categoryEntity = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CategoryEntity not found with ID: " + id));
        categoryRepository.delete(categoryEntity);
    }

    /**
     * Helper method to update categoryEntity details from a CategoryDTO.
     *
     * @param categoryEntity    the categoryEntity entity to update
     * @param categoryDTO the source DTO containing updated details
     */
    private void updateCategoryDetails(CategoryEntity categoryEntity, CategoryDTO categoryDTO) {
        categoryEntity.setTitle(categoryDTO.getTitle());
        categoryEntity.setAlias(categoryDTO.getAlias());
        categoryEntity.setImageURL(categoryDTO.getImageURL());
        categoryEntity.setEnabled(categoryDTO.getEnabled());
    }

}