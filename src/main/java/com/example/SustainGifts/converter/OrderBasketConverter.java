package com.example.SustainGifts.converter;

import com.example.SustainGifts.dtos.OrderBasketDTO;
import com.example.SustainGifts.models.OrderBasketEntity;
import com.example.SustainGifts.models.ProductEntity;
import com.example.SustainGifts.models.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class OrderBasketConverter {

    /**
     * Converts an OrderBasketEntity to an OrderBasketDTO.
     *
     * @param orderBasketEntity the OrderBasketEntity to convert
     * @return the converted OrderBasketDTO
     */
    public OrderBasketDTO convertToDTO(OrderBasketEntity orderBasketEntity) {
        return OrderBasketDTO.builder()
                .id(orderBasketEntity.getId())
                .quantity(orderBasketEntity.getQuantity())
                .productId(orderBasketEntity.getProductEntity().getId())
                .userId(orderBasketEntity.getUserEntity().getId())
                .build();
    }

    /**
     * Converts an OrderBasketDTO to an OrderBasketEntity.
     *
     * @param orderBasketDTO the OrderBasketDTO to convert
     * @param productEntity  the associated ProductEntity
     * @param userEntity     the associated UserEntity
     * @return the converted OrderBasketEntity
     */
    public OrderBasketEntity convertToEntity(OrderBasketDTO orderBasketDTO, ProductEntity productEntity, UserEntity userEntity) {
        return OrderBasketEntity.builder()
                .id(orderBasketDTO.getId())
                .quantity(orderBasketDTO.getQuantity())
                .productEntity(productEntity)
                .userEntity(userEntity)
                .build();
    }
}