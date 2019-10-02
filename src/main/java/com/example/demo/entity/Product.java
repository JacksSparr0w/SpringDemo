package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "product")

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    @NotBlank(message = "name can't be null")
    private String name;
    @OneToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "type_id")
    @NotNull(message = "choose type")
    private Type type;
    @Column
    @Digits(integer = 3, fraction = 1, message = "invalid price")
    private double price;
    @Transient
    private double actualPrice;

    public void updateActualPrice(Double coefficient) {
        actualPrice = price * coefficient;
    }
}
