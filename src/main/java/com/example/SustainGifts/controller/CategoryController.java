package com.example.SustainGifts.controller;


import com.example.SustainGifts.dtos.CategoryDTO;
import com.example.SustainGifts.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    /**
     * Get all categories.
     *
     * @return a list of CategoryDTOs
     */
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<CategoryDTO> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    /**
     * Get a category by ID.
     *
     * @param id the ID of the category to retrieve
     * @return the CategoryDTO or throws a ResourceNotFoundException if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Integer id) {
        CategoryDTO category = categoryService.getCategoryById(id);
        if (category == null){
            throw new ResourceNotFoundException("Category not found with ID: "+id);
        }
        return ResponseEntity.ok(category);
    }

    /**
     * Create a new category.
     *
     * @param categoryDTO the CategoryDTO to create
     * @return the created CategoryDTO with status 201
     */
    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO) {
        CategoryDTO newCategory = categoryService.createCategory(categoryDTO);
        return ResponseEntity.status(201).body(newCategory);
    }

    /**
     * Update a category by ID.
     *
     * @param id the ID of the category to update
     * @param categoryDTO the updated CategoryDTO
     * @return the updated CategoryDTO
     */
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Integer id, @RequestBody CategoryDTO categoryDTO) {
        CategoryDTO updatedCategory = categoryService.updateCategory(id, categoryDTO);
        return ResponseEntity.ok(updatedCategory);
    }

    /**
     * Delete a category by ID.
     *
     * @param id the ID of the category to delete
     * @return a response entity with no content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Integer id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}