package com.example.SustainGifts.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {

    private Integer id;
    private String title;
    private String alias;
    private String description;
    private int price;
    private String imageURL;
    private Integer vendorId;
    private String vendorTitle;
    private Integer categoryId;
    private String categoryTitle;
    private String shortTitle;
    private String shortAlias;
    private String shortDescription;

}
