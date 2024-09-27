package com.example.SustainGifts.models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;
import lombok.*;



@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "category")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "title", nullable = false, length = 155)
    private String title;

    @Column(name = "alias", length = 155)
    private String alias;

    @Column(name = "image")
    private String imageURL;

    @Column(name = "enabled", nullable = false)
    private Boolean enabled;

    @Column(name = "all_parent_ids")
    @ToString.Exclude
    private String allParentsIDs;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private CategoryEntity parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OrderBy("title asc")
    @ToString.Exclude
    private Set<CategoryEntity> children = new HashSet<>();

    @OneToMany(mappedBy = "categoryEntity", fetch = FetchType.LAZY, cascade = {CascadeType.ALL, CascadeType.PERSIST})
    @ToString.Exclude
    private List<ProductEntity> productEntities;

    public static CategoryEntity copyIdAndTitle(CategoryEntity categoryEntity) {
        return CategoryEntity.builder()
                .id(categoryEntity.getId())
                .title(categoryEntity.getTitle())
                .build();
    }

    public static CategoryEntity copyIdAndTitle(Integer id, String title) {
        return CategoryEntity.builder()
                .id(id)
                .title(title)
                .build();
    }

    public static CategoryEntity copyFull(CategoryEntity categoryEntity) {
        return CategoryEntity.builder()
                .id(categoryEntity.getId())
                .title(categoryEntity.getTitle())
                .alias(categoryEntity.getAlias())
                .imageURL(categoryEntity.getImageURL())
                .enabled(categoryEntity.getEnabled())
                .build();
    }

    public static CategoryEntity copyFull(CategoryEntity categoryEntity, String title) {
        return CategoryEntity.builderFrom(categoryEntity)// Manually create the builder
                .title(title)
                .build();
    }

    public CategoryEntity(Integer id) {
        this.id = id;
    }

    public CategoryEntity(String title, CategoryEntity parent) {
        this(title);
        this.parent = parent;
    }

    public CategoryEntity(String title) {
        this.title = title;
        this.alias = title;
        this.imageURL = "default.png";
    }

    public CategoryEntity(Integer id, String title, String alias, String imageURL, Boolean enabled, CategoryEntity parent) {
        this.id = id;
        this.title = title;
        this.alias = alias;
        this.imageURL = imageURL;
        this.enabled = enabled;
        this.parent = parent;
    }

    public static CategoryEntityBuilder builderFrom(CategoryEntity categoryEntity) {
        return CategoryEntity.builder()
                .id(categoryEntity.getId())
                .title(categoryEntity.getTitle())
                .alias(categoryEntity.getAlias())
                .imageURL(categoryEntity.getImageURL())
                .enabled(categoryEntity.getEnabled())
                .parent(categoryEntity.getParent());
    }
}
