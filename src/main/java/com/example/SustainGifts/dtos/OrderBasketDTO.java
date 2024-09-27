package com.example.SustainGifts.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderBasketDTO {
    private Integer id;
    private int quantity;
    private Integer productId;
    private Integer userId;
}
