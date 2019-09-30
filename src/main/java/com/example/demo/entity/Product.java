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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    @NotBlank(message = "name can't be null")
    private String name;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    @NotNull(message = "choose type")
    private Type type;
    @Column
    @Digits(integer = 3, fraction = 0, message = "invalid price")
    //todo read about digits
    private int price;
}
