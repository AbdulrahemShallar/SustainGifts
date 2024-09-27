package com.example.SustainGifts.services;

import com.example.SustainGifts.converter.OrderConverter;
import com.example.SustainGifts.dtos.OrderDTO;
import com.example.SustainGifts.models.OrderEntity;
import com.example.SustainGifts.models.UserEntity;
import com.example.SustainGifts.repositories.OrderRepository;
import com.example.SustainGifts.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderConverter orderConverter;

    /**
     * Get all orders and convert them to DTOs.
     *
     * @return a list of OrderDTOs
     */
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(orderConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Get an order by ID and convert it to DTO.
     *
     * @param id the ID of the order
     * @return the corresponding OrderDTO
     */
    public OrderDTO getOrderById(Integer id) {
        return orderRepository.findById(id)
                .map(orderConverter::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + id));
    }

    /**
     * Save a new order.
     *
     * @param orderDTO the OrderDTO to save
     * @return the saved OrderDTO
     */
    public OrderDTO saveOrder(OrderDTO orderDTO) {
        UserEntity userEntity = userRepository.findById(orderDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + orderDTO.getUserId()));

        OrderEntity orderEntity = orderConverter.convertToEntity(orderDTO, userEntity);
        OrderEntity savedOrderEntity = orderRepository.save(orderEntity);
        return orderConverter.convertToDTO(savedOrderEntity);
    }

    /**
     * Update an existing order.
     *
     * @param id       the ID of the order to update
     * @param orderDTO the updated OrderDTO
     * @return the updated OrderDTO
     */
    public OrderDTO updateOrder(Integer id, OrderDTO orderDTO) {
        OrderEntity existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + id));

        existingOrder.setOrderStatus(orderDTO.getOrderStatus());
        existingOrder.setShippingType(orderDTO.getShippingType());
        existingOrder.setCity(orderDTO.getCity());
        existingOrder.setAddress(orderDTO.getAddress());
        existingOrder.setTotalPrice(orderDTO.getTotalPrice());

        OrderEntity updatedOrder = orderRepository.save(existingOrder);
        return orderConverter.convertToDTO(updatedOrder);
    }


    /**
     * Delete an order by ID.
     *
     * @param id the ID of the order to delete
     * @return true if the order was deleted, false otherwise
     */
    public boolean deleteOrder(Integer id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return true;
        }
        return false;
    }
}