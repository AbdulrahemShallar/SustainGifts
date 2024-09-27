package com.example.SustainGifts.services;

import com.example.SustainGifts.converter.OrderBasketConverter;
import com.example.SustainGifts.dtos.OrderBasketDTO;
import com.example.SustainGifts.models.OrderBasketEntity;
import com.example.SustainGifts.models.ProductEntity;
import com.example.SustainGifts.models.UserEntity;
import com.example.SustainGifts.repositories.OrderBasketRepository;
import com.example.SustainGifts.repositories.ProductRepository;
import com.example.SustainGifts.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderBasketService {

    @Autowired
    private OrderBasketRepository orderBasketRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderBasketConverter orderBasketConverter;

    /**
     * Get all order baskets and convert them to DTOs.
     *
     * @return a list of OrderBasketDTOs
     */
    public List<OrderBasketDTO> getAllOrderBaskets() {
        return orderBasketRepository.findAll()
                .stream()
                .map(orderBasketConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Get an order basket by ID and convert it to DTO.
     *
     * @param id the ID of the order basket
     * @return the corresponding OrderBasketDTO
     */
    public OrderBasketDTO getOrderBasketById(Integer id) {
        return orderBasketRepository.findById(id)
                .map(orderBasketConverter::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Order basket not found with ID: " + id));
    }

    /**
     * Save a new order basket.
     *
     * @param orderBasketDTO the OrderBasketDTO to save
     * @return the saved OrderBasketDTO
     */
    public OrderBasketDTO saveOrderBasket(OrderBasketDTO orderBasketDTO) {
        ProductEntity productEntity = productRepository.findById(orderBasketDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + orderBasketDTO.getProductId()));

        UserEntity userEntity = userRepository.findById(orderBasketDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + orderBasketDTO.getUserId()));

        OrderBasketEntity orderBasketEntity = orderBasketConverter.convertToEntity(orderBasketDTO, productEntity, userEntity);
        OrderBasketEntity savedOrderBasket = orderBasketRepository.save(orderBasketEntity);
        return orderBasketConverter.convertToDTO(savedOrderBasket);
    }

    /**
     * Update an existing order basket.
     *
     * @param id            the ID of the order basket to update
     * @param orderBasketDTO the updated OrderBasketDTO
     * @return the updated OrderBasketDTO
     */
    public OrderBasketDTO updateOrderBasket(Integer id, OrderBasketDTO orderBasketDTO) {
        OrderBasketEntity existingOrderBasket = orderBasketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order basket not found with ID: " + id));

        ProductEntity productEntity = productRepository.findById(orderBasketDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + orderBasketDTO.getProductId()));

        UserEntity userEntity = userRepository.findById(orderBasketDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + orderBasketDTO.getUserId()));

        existingOrderBasket.setQuantity(orderBasketDTO.getQuantity());
        existingOrderBasket.setProductEntity(productEntity);
        existingOrderBasket.setUserEntity(userEntity);

        OrderBasketEntity updatedOrderBasket = orderBasketRepository.save(existingOrderBasket);
        return orderBasketConverter.convertToDTO(updatedOrderBasket);
    }

    /**
     * Delete an order basket by ID.
     *
     * @param id the ID of the order basket to delete
     * @return true if the order basket was deleted, false otherwise
     */
    public boolean deleteOrderBasket(Integer id) {
        if (orderBasketRepository.existsById(id)) {
            orderBasketRepository.deleteById(id);
            return true;
        }
        return false;
    }
}