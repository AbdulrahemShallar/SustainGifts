package com.example.SustainGifts.services;

import com.example.SustainGifts.converter.CategoryConverter;
import com.example.SustainGifts.dtos.CategoryDTO;
import com.example.SustainGifts.models.CategoryEntity;
import com.example.SustainGifts.repositories.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {
    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryConverter categoryConverter;

    @InjectMocks
    private CategoryService categoryService;

    @Test
    void getAllCategories_Success() {
        // Arrange
        List<CategoryEntity> categoryEntities = List.of(
                CategoryEntity.builder().id(1).title("Category 1").build(),
                CategoryEntity.builder().id(2).title("Category 2").build()
        );

        when(categoryRepository.findAll()).thenReturn(categoryEntities);
        when(categoryConverter.convertToDTO(any(CategoryEntity.class)))
                .thenAnswer(invocation -> {
                    CategoryEntity entity = invocation.getArgument(0);
                    return CategoryDTO.builder()
                            .id(entity.getId())
                            .title(entity.getTitle())
                            .build();
                });

        // Act
        List<CategoryDTO> result = categoryService.getAllCategories();

        // Assert
        assertEquals(2, result.size());
        verify(categoryRepository, times(1)).findAll();
        verify(categoryConverter, times(2)).convertToDTO(any(CategoryEntity.class));
    }

    @Test
    void getCategoryById_NotFound() {
        // Arrange
        when(categoryRepository.findById(1)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            categoryService.getCategoryById(1);
        });

        assertEquals("CategoryEntity not found with ID: 1", exception.getMessage());
    }
}