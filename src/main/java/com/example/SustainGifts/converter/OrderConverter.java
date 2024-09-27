package com.example.SustainGifts.converter;

import com.example.SustainGifts.dtos.OrderDTO;
import com.example.SustainGifts.models.OrderEntity;
import com.example.SustainGifts.models.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class OrderConverter {
    /**
     * Converts an OrderEntity to an OrderDTO.
     *
     * @param orderEntity the OrderEntity to convert
     * @return the converted OrderDTO
     */
    public OrderDTO convertToDTO(OrderEntity orderEntity) {
        return OrderDTO.builder()
                .id(orderEntity.getId())
                .orderStatus(orderEntity.getOrderStatus())
                .shippingType(orderEntity.getShippingType())
                .city(orderEntity.getCity())
                .address(orderEntity.getAddress())
                .totalPrice(orderEntity.getTotalPrice())
                .userId(orderEntity.getUserEntity().getId())
                .userName(orderEntity.getUserEntity().getLogin())  // Assuming 'login' is username
                .build();
    }

    /**
     * Converts an OrderDTO to an OrderEntity.
     *
     * @param orderDTO the OrderDTO to convert
     * @param userEntity the associated UserEntity
     * @return the converted OrderEntity
     */
    public OrderEntity convertToEntity(OrderDTO orderDTO, UserEntity userEntity) {
        return OrderEntity.builder()
                .id(orderDTO.getId())
                .orderStatus(orderDTO.getOrderStatus())
                .shippingType(orderDTO.getShippingType())
                .city(orderDTO.getCity())
                .address(orderDTO.getAddress())
                .totalPrice(orderDTO.getTotalPrice())
                .userEntity(userEntity)
                .build();
    }
}