package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "producer")
@Data
public class Producer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column
    private String address;
    @Column
    private long phone;
    @Column(name = "head_name")
    private String headName;
    @Column(name = "head_position")
    private String headPosition;
    @Column(name = "phone_marketing_department")
    private long phoneMarketingDepartment;
    @Column(name = "phone_marketing_head")
    private long phoneMarketingHead;
    @Column(name = "contact_name")
    private String contactName;
}
