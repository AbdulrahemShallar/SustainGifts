package com.example.SustainGifts.controller;

import com.example.SustainGifts.dtos.OrderBasketDTO;
import com.example.SustainGifts.services.OrderBasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-baskets")
public class OrderBasketController {

    @Autowired
    private OrderBasketService orderBasketService;

    /**
     * Get all order baskets.
     *
     * @return a list of OrderBasketDTOs
     */
    @GetMapping
    public ResponseEntity<List<OrderBasketDTO>> getAllOrderBaskets() {
        List<OrderBasketDTO> orderBaskets = orderBasketService.getAllOrderBaskets();
        return ResponseEntity.ok(orderBaskets);
    }

    /**
     * Get an order basket by ID.
     *
     * @param id the ID of the order basket to retrieve
     * @return the OrderBasketDTO
     */
    @GetMapping("/{id}")
    public ResponseEntity<OrderBasketDTO> getOrderBasketById(@PathVariable Integer id) {
        OrderBasketDTO orderBasket = orderBasketService.getOrderBasketById(id);
        return ResponseEntity.ok(orderBasket);
    }

    /**
     * Create a new order basket.
     *
     * @param orderBasketDTO the OrderBasketDTO to create
     * @return the created OrderBasketDTO
     */
    @PostMapping
    public ResponseEntity<OrderBasketDTO> createOrderBasket(@RequestBody OrderBasketDTO orderBasketDTO) {
        OrderBasketDTO savedOrderBasket = orderBasketService.saveOrderBasket(orderBasketDTO);
        return ResponseEntity.ok(savedOrderBasket);
    }

    /**
     * Update an existing order basket.
     *
     * @param id             the ID of the order basket to update
     * @param orderBasketDTO the updated OrderBasketDTO
     * @return the updated OrderBasketDTO
     */
    @PutMapping("/{id}")
    public ResponseEntity<OrderBasketDTO> updateOrderBasket(@PathVariable Integer id, @RequestBody OrderBasketDTO orderBasketDTO) {
        OrderBasketDTO updatedOrderBasket = orderBasketService.updateOrderBasket(id, orderBasketDTO);
        return ResponseEntity.ok(updatedOrderBasket);
    }

    /**
     * Delete an order basket by ID.
     *
     * @param id the ID of the order basket to delete
     * @return a response entity indicating success or failure
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderBasket(@PathVariable Integer id) {
        boolean isDeleted = orderBasketService.deleteOrderBasket(id);
        if (isDeleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}