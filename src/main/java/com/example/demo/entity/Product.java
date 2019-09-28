package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;

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
    private Type type;
    @Column
    @Digits(integer = 1, fraction = 0, message = "invalid price")
    //todo read about digits
    private int price;
}
