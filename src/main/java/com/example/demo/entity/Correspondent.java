package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "correspondent")
@EqualsAndHashCode
public class Correspondent {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String surname;
    @Column
    private String name;
    @Column
    private String lastName;
    @Column
    private String position;
    @Column
    private String division;
    @Column
    private boolean chief;
}
