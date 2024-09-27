package com.example.SustainGifts.models;

import java.util.List;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"vendorEntity", "categoryEntity", "orderBasketEntities"})
@Builder
@Entity
@Table(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "alias", length = 40)
    private String alias;

    @Column(name = "description")
    private String description;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "image")
    private String imageURL;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "vendor_id", nullable = false)
    private VendorEntity vendorEntity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity categoryEntity;

    @OneToMany(mappedBy = "productEntity", fetch = FetchType.LAZY)
    private List<OrderBasketEntity> orderBasketEntities;

    @Transient
    public String getShortTitle() {
        return (title != null && title.length() > 40) ? title.substring(0, 40).concat("...") : title;
    }

    @Transient
    public String getShortAlias() {
        return (alias != null && alias.length() > 40) ? alias.substring(0, 40).concat("...") : alias;
    }

    @Transient
    public String getShortDescription() {
        return (description != null && description.length() > 50) ? description.substring(0, 50).concat("...") : description;
    }
}
