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
@Table(name = "event")
@EqualsAndHashCode
public class Event {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String name;
    @Column
    private long startDate;
    @Column
    private long endDate;
    @Column
    private long corespondentID;
    @Column
    private long orderID;
    @Column
    private boolean completed;
}
