package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "product")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Type type;
    @Column
    private int price;
}
