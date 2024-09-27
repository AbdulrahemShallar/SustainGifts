package com.example.SustainGifts.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"userInfoEntity", "orderBasketEntities", "orderEntities"})
@Builder
@Entity
@Table(name = "\"user\"")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "login", nullable = false, unique = true)
    private String login;

    @Column(name = "password", nullable = false)
    @JsonIgnore
    private String password;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "userEntity", cascade = CascadeType.ALL)
    private UserInfoEntity userInfoEntity;

    @OneToMany(mappedBy = "userEntity", cascade = {CascadeType.ALL, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private List<OrderBasketEntity> orderBasketEntities;

    @OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY)
    private List<OrderEntity> orderEntities;

}