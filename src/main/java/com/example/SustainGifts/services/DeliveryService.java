package com.example.SustainGifts.services;


import com.example.SustainGifts.converter.DeliveryConverter;
import com.example.SustainGifts.dtos.DeliveryDTO;
import com.example.SustainGifts.models.DeliveryEntity;
import com.example.SustainGifts.models.DeliveryStatus;
import com.example.SustainGifts.models.OrderEntity;
import com.example.SustainGifts.repositories.DeliveryRepository;
import com.example.SustainGifts.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private DeliveryConverter deliveryConverter;

    /**
     * Get Delivery information by order ID.
     *
     * @param orderId the ID of the order
     * @return the corresponding DeliveryDTO
     */
    public DeliveryDTO getDeliveryByOrderId(Integer orderId) {
        return deliveryRepository.findById(orderId)
                .map(deliveryConverter::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Delivery not found for order ID: " + orderId));
    }

    /**
     * Create or update Delivery information.
     *
     * @param deliveryDTO the data to save
     * @return the saved DeliveryDTO
     */
    public DeliveryDTO saveDelivery(DeliveryDTO deliveryDTO) {
        OrderEntity orderEntity = orderRepository.findById(deliveryDTO.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + deliveryDTO.getOrderId()));

        DeliveryEntity deliveryEntity = deliveryConverter.convertToEntity(deliveryDTO, orderEntity);
        DeliveryEntity savedDelivery = deliveryRepository.save(deliveryEntity);

        return deliveryConverter.convertToDTO(savedDelivery);
    }

    /**
     * Update the delivery status for an order.
     *
     * @param orderId the ID of the order
     * @param status  the new DeliveryStatus
     * @return the updated DeliveryDTO
     */
    public DeliveryDTO updateDeliveryStatus(Integer orderId, DeliveryStatus status) {
        DeliveryEntity deliveryEntity = deliveryRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Delivery not found for order ID: " + orderId));

        deliveryEntity.setStatus(status);
        DeliveryEntity updatedDelivery = deliveryRepository.save(deliveryEntity);

        return deliveryConverter.convertToDTO(updatedDelivery);
    }

    /**
     * Delete a Delivery by order ID.
     *
     * @param orderId the ID of the order
     */
    public void deleteDeliveryByOrderId(Integer orderId) {
        deliveryRepository.deleteById(orderId);
    }
}
