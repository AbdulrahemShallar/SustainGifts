package com.example.SustainGifts.models;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = "productEntities")
@Builder
@Entity
@Table(name = "vendor")
public class VendorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "title", nullable = false)
    private String title;

    @OneToMany(mappedBy = "vendorEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProductEntity> productEntities;

}