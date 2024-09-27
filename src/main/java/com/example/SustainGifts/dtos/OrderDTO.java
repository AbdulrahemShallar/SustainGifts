package com.example.SustainGifts.dtos;

import com.example.SustainGifts.models.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO {
    private Integer id;
    private OrderStatus orderStatus;
    private Integer shippingType;
    private String city;
    private String address;
    private Float totalPrice;
    private Integer userId;
    private String userName;
}
