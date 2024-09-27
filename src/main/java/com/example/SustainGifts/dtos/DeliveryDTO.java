package com.example.SustainGifts.dtos;

import com.example.SustainGifts.models.DeliveryStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryDTO {
    private Integer id;
    private DeliveryStatus status;
    private Integer orderId;
}
