package com.example.SustainGifts.controller;


import com.example.SustainGifts.dtos.OrderDTO;
import com.example.SustainGifts.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * Get all orders.
     *
     * @return a list of OrderDTOs
     */
    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<OrderDTO> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    /**
     * Get an order by ID.
     *
     * @param id the ID of the order to retrieve
     * @return the OrderDTO
     */
    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Integer id) {
        OrderDTO order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    /**
     * Create a new order.
     *
     * @param orderDTO the OrderDTO to create
     * @return the created OrderDTO
     */
    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        OrderDTO savedOrder = orderService.saveOrder(orderDTO);
        return ResponseEntity.ok(savedOrder);
    }

    /**
     * Update an existing order.
     *
     * @param id       the ID of the order to update
     * @param orderDTO the updated OrderDTO
     * @return the updated OrderDTO
     */
    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable Integer id, @RequestBody OrderDTO orderDTO) {
        OrderDTO updatedOrder = orderService.updateOrder(id, orderDTO);
        return ResponseEntity.ok(updatedOrder);
    }



    /**
     * Delete an order by ID.
     *
     * @param id the ID of the order to delete
     * @return a response entity indicating success or failure
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Integer id) {
        boolean isDeleted = orderService.deleteOrder(id);
        if (isDeleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}