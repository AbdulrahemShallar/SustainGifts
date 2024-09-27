package com.example.SustainGifts.controller;

import com.example.SustainGifts.dtos.DeliveryDTO;
import com.example.SustainGifts.models.DeliveryStatus;
import com.example.SustainGifts.services.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/delivery")
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    /**
     * Get Delivery information by order ID.
     *
     * @param orderId the ID of the order
     * @return the DeliveryDTO
     */
    @GetMapping("/{orderId}")
    public ResponseEntity<DeliveryDTO> getDeliveryByOrderId(@PathVariable Integer orderId) {
        DeliveryDTO deliveryDTO = deliveryService.getDeliveryByOrderId(orderId);
        return ResponseEntity.ok(deliveryDTO);
    }

    /**
     * Create or update delivery information.
     *
     * @param deliveryDTO the delivery information to save
     * @return the saved DeliveryDTO
     */
    @PostMapping
    public ResponseEntity<DeliveryDTO> saveDelivery(@RequestBody DeliveryDTO deliveryDTO) {
        DeliveryDTO savedDelivery = deliveryService.saveDelivery(deliveryDTO);
        return ResponseEntity.ok(savedDelivery);
    }

    /**
     * Update the delivery status of an order.
     *
     * @param orderId the ID of the order
     * @param status  the new status
     * @return the updated DeliveryDTO
     */
    @PatchMapping("/{orderId}/status")
    public ResponseEntity<DeliveryDTO> updateDeliveryStatus(@PathVariable Integer orderId, @RequestBody DeliveryStatus status) {
        DeliveryDTO updatedDelivery = deliveryService.updateDeliveryStatus(orderId, status);
        return ResponseEntity.ok(updatedDelivery);
    }

    /**
     * Delete delivery by order ID.
     *
     * @param orderId the ID of the order
     * @return ResponseEntity
     */
    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteDeliveryByOrderId(@PathVariable Integer orderId) {
        deliveryService.deleteDeliveryByOrderId(orderId);
        return ResponseEntity.noContent().build();
    }
}