package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String content;
    @Column
    private Date startDate;
    @Column
    private Date owner;
}
