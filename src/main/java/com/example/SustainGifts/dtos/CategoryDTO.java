package com.example.SustainGifts.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDTO {
    private Integer id;
    private String title;
    private String alias;
    private String imageURL;
    private Boolean enabled;
    private String allParentsIDs;
    private Integer parentId;
    private Integer parentTitle;
    private Set<CategoryDTO> children;
    private List<ProductDTO> products;
}



