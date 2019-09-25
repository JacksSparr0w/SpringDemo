package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "type")
@Data
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
}
