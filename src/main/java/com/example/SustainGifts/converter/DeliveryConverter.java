package com.example.SustainGifts.converter;

import com.example.SustainGifts.dtos.DeliveryDTO;
import com.example.SustainGifts.models.DeliveryEntity;
import com.example.SustainGifts.models.OrderEntity;
import org.springframework.stereotype.Component;

@Component
public class DeliveryConverter {

    /**
     * Converts a DeliveryEntity to a DeliveryDTO.
     *
     * @param deliveryEntity the DeliveryEntity to convert
     * @return the converted DeliveryDTO
     */
    public DeliveryDTO convertToDTO(DeliveryEntity deliveryEntity) {
        return DeliveryDTO.builder()
                .id(deliveryEntity.getId())
                .status(deliveryEntity.getStatus())
                .orderId(deliveryEntity.getOrderEntity().getId())
                .build();
    }

    /**
     * Converts a DeliveryDTO to a DeliveryEntity.
     *
     * @param deliveryDTO the DeliveryDTO to convert
     * @param orderEntity the associated OrderEntity
     * @return the converted DeliveryEntity
     */
    public DeliveryEntity convertToEntity(DeliveryDTO deliveryDTO, OrderEntity orderEntity) {
        return DeliveryEntity.builder()
                .id(deliveryDTO.getId())
                .status(deliveryDTO.getStatus())
                .orderEntity(orderEntity)
                .build();
    }
}
