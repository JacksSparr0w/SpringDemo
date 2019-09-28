package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data

@Entity
@Table(name = "production")
public class Production {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Producer producer;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Product product;
    @Column
    private Date date;
    @Column
    private String place;
}
