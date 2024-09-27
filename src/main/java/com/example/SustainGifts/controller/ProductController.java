package com.example.SustainGifts.controller;


import com.example.SustainGifts.dtos.ProductDTO;
import com.example.SustainGifts.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;


    /**
     * Get all products.
     *
     * @return a list of ProductDTOs and HTTP status 200 (OK)
     */
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /**
     * Get a product by ID.
     *
     * @param id the ID of the product to retrieve
     * @return the ProductDTO and HTTP status 200 (OK) if found, otherwise HTTP status 404 (Not Found)
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Integer id) {
        ProductDTO product = productService.getProductById(id);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Create a new product.
     *
     * @param productDTO the ProductDTO to create
     * @return the created ProductDTO and HTTP status 201 (Created)
     */
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        ProductDTO createdProduct = productService.saveProduct(productDTO);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }


    /**
     * Update an existing product by ID.
     *
     * @param id         the ID of the product to update
     * @param productDTO the updated ProductDTO
     * @return the updated ProductDTO and HTTP status 200 (OK) if found, otherwise HTTP status 404 (Not Found)
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Integer id, @RequestBody ProductDTO productDTO) {
        ProductDTO updatedProduct = productService.updateProduct(id, productDTO);
        if (updatedProduct != null) {
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Delete a product by ID.
     *
     * @param id the ID of the product to delete
     * @return HTTP status 204 (No Content) if successful, otherwise HTTP status 404 (Not Found)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        boolean isDeleted = productService.deleteProduct(id);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
