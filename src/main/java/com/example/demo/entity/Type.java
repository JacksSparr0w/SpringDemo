package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "type")
public class Type {
    @Id
    @GeneratedValue
    private Integer id;
    @Column()
    private String name;
}
