package com.example.SustainGifts.controller;

import com.example.SustainGifts.dtos.VendorDTO;
import com.example.SustainGifts.services.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendors")
public class VendorController {


    @Autowired
    private VendorService vendorService;

    /**
     * Get all vendors.
     *
     * @return a list of VendorDTOs
     */
    @GetMapping
    public ResponseEntity<List<VendorDTO>> getAllVendors() {
        List<VendorDTO> vendors = vendorService.getAllVendors();
        return ResponseEntity.ok(vendors);
    }

    /**
     * Get a vendor by ID.
     *
     * @param id the ID of the vendor to retrieve
     * @return the VendorDTO
     */
    @GetMapping("/{id}")
    public ResponseEntity<VendorDTO> getVendorById(@PathVariable Integer id) {
        VendorDTO vendor = vendorService.getVendorById(id);
        if (vendor == null){
            throw new ResourceNotFoundException("Vendor not found with ID: "+id);
        }
        return ResponseEntity.ok(vendor);
    }

    /**
     * Create a new vendor.
     *
     * @param vendorDTO the VendorDTO to create
     * @return the created VendorDTO
     */
    @PostMapping
    public ResponseEntity<VendorDTO> createVendor(@RequestBody VendorDTO vendorDTO) {
        VendorDTO savedVendor = vendorService.saveVendor(vendorDTO);
        return ResponseEntity.ok(savedVendor);
    }

    /**
     * Update an existing vendor.
     *
     * @param id        the ID of the vendor to update
     * @param vendorDTO the updated VendorDTO
     * @return the updated VendorDTO
     */
    @PutMapping("/{id}")
    public ResponseEntity<VendorDTO> updateVendor(@PathVariable Integer id, @RequestBody VendorDTO vendorDTO) {
        VendorDTO updatedVendor = vendorService.updateVendor(id, vendorDTO);
        return ResponseEntity.ok(updatedVendor);
    }

    /**
     * Delete a vendor by ID.
     *
     * @param id the ID of the vendor to delete
     * @return a response entity indicating success or failure
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVendor(@PathVariable Integer id) {
        if (!vendorService.deleteVendor(id)) {
            throw new ResourceNotFoundException("Vendor not found with ID: " + id);
        }
        return ResponseEntity.ok().build();
    }
}