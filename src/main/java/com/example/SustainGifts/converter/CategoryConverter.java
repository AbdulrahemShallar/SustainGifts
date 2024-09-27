package com.example.SustainGifts.converter;

import com.example.SustainGifts.dtos.CategoryDTO;
import com.example.SustainGifts.models.CategoryEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CategoryConverter {

    /**
     * Converts a CategoryEntity entity to a CategoryDTO.
     *
     * @param categoryEntity the CategoryEntity entity to convert
     * @return the converted CategoryDTO
     */
    public CategoryDTO convertToDTO(CategoryEntity categoryEntity) {
        return CategoryDTO.builder()
                .id(categoryEntity.getId())
                .title(categoryEntity.getTitle())
                .alias(categoryEntity.getAlias())
                .imageURL(categoryEntity.getImageURL())
                .enabled(categoryEntity.getEnabled())
                .parentId(Optional.ofNullable(categoryEntity.getParent()).map(CategoryEntity::getId).orElse(null))  // Optional to handle null
                .children(convertChildrenToDTO(categoryEntity.getChildren()))  // Extracted logic to a method for clarity
                .build();
    }

    /**
     * Converts a CategoryDTO to a CategoryEntity entity.
     *
     * @param categoryDTO the CategoryDTO to convert
     * @return the converted CategoryEntity entity
     */
    public CategoryEntity convertToEntity(CategoryDTO categoryDTO) {
        CategoryEntity parentCategoryEntity = Optional
                .ofNullable(categoryDTO.getParentId())
                .map(CategoryEntity::new)
                .orElse(null);  // Optional for parent handling

        return CategoryEntity.builder()
                .id(categoryDTO.getId())
                .title(categoryDTO.getTitle())
                .alias(categoryDTO.getAlias())
                .imageURL(categoryDTO.getImageURL())
                .enabled(categoryDTO.getEnabled())
                .parent(parentCategoryEntity)
                .build();
    }

    /**
     * Converts a set of child CategoryEntity entities to a set of CategoryDTOs.
     *
     * @param children the set of child CategoryEntity entities
     * @return the set of converted CategoryDTOs
     */
    private Set<CategoryDTO> convertChildrenToDTO(Set<CategoryEntity> children) {
        return children.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toSet());
    }
}
