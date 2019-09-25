package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "production")
@Data
public class Production {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinTable(name = "producer_id")
    private Producer producer;
    @ManyToOne
    @JoinTable(name = "product_id")
    private Product product;
    @Column
    private Date date;
    @Column
    private String place;
}
