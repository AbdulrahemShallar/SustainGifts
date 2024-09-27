package com.example.SustainGifts.converter;

import com.example.SustainGifts.dtos.VendorDTO;
import com.example.SustainGifts.models.VendorEntity;
import org.springframework.stereotype.Component;

@Component
public class VendorConverter {
    /**
     * Converts a VendorEntity to a VendorDTO.
     *
     * @param vendorEntity the VendorEntity to convert
     * @return the converted VendorDTO
     */
    public VendorDTO convertToDTO(VendorEntity vendorEntity) {
        return VendorDTO.builder()
                .id(vendorEntity.getId())
                .title(vendorEntity.getTitle())
                .build();
    }

    /**
     * Converts a VendorDTO to a VendorEntity.
     *
     * @param vendorDTO the VendorDTO to convert
     * @return the converted VendorEntity
     */
    public VendorEntity convertToEntity(VendorDTO vendorDTO) {
        return VendorEntity.builder()
                .id(vendorDTO.getId())
                .title(vendorDTO.getTitle())
                .build();
    }
}
