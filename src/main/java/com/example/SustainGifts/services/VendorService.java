package com.example.SustainGifts.services;

import com.example.SustainGifts.converter.VendorConverter;
import com.example.SustainGifts.dtos.VendorDTO;
import com.example.SustainGifts.models.VendorEntity;
import com.example.SustainGifts.repositories.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorService {

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private VendorConverter vendorConverter;

    /**
     * Retrieve all vendors and convert them to DTOs.
     *
     * @return a list of VendorDTOs
     */
    public List<VendorDTO> getAllVendors() {
        return vendorRepository.findAll()
                .stream()
                .map(vendorConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieve a vendor by ID and convert it to a DTO.
     *
     * @param id the ID of the vendor
     * @return the corresponding VendorDTO
     * @throws RuntimeException if no vendor is found with the given ID
     */
    public VendorDTO getVendorById(Integer id) {
        return vendorRepository.findById(id)
                .map(vendorConverter::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Vendor not found with ID: " + id));
    }

    /**
     * Save a new vendor.
     *
     * @param vendorDTO the VendorDTO to save
     * @return the saved VendorDTO
     */
    public VendorDTO saveVendor(VendorDTO vendorDTO) {
        VendorEntity vendorEntity = vendorConverter.convertToEntity(vendorDTO);
        VendorEntity savedVendorEntity = vendorRepository.save(vendorEntity);
        return vendorConverter.convertToDTO(savedVendorEntity);
    }

    /**
     * Update an existing vendor.
     *
     * @param id        the ID of the vendor to update
     * @param vendorDTO the updated VendorDTO
     * @return the updated VendorDTO
     * @throws RuntimeException if no vendor is found with the given ID
     */
    public VendorDTO updateVendor(Integer id, VendorDTO vendorDTO) {
        VendorEntity existingVendorEntity = vendorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vendor not found with ID: " + id));

        existingVendorEntity.setTitle(vendorDTO.getTitle());

        VendorEntity updatedVendorEntity = vendorRepository.save(existingVendorEntity);
        return vendorConverter.convertToDTO(updatedVendorEntity);
    }

    /**
     * Delete a vendor by ID.
     *
     * @param id the ID of the vendor to delete
     * @return true if the vendor was deleted, false if not found
     */
    public boolean deleteVendor(Integer id) {
        if (vendorRepository.existsById(id)) {
            vendorRepository.deleteById(id);
            return true;
        }
        return false;
    }
}