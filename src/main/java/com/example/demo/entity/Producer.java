package com.example.demo.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "producer")

public class Producer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    @NotBlank(message = "name can't be null")
    private String name;
    @Column
    @NotBlank(message = "address can't be null")
    private String address;
    @Column
    @Digits(integer = 7, fraction = 0, message = "invalid phone format")
    private long phone;
    @Column(name = "head_name")
    private String headName;
    @Column(name = "head_position")
    private String headPosition;
    @Column(name = "phone_marketing_department")
    @Digits(integer = 7, fraction = 0, message = "invalid phone format")
    private long phoneMarketingDepartment;
    @Column(name = "phone_marketing_head")
    @Digits(integer = 7, fraction = 0, message = "invalid phone format")
    private long phoneMarketingHead;
    @Column(name = "contact_name")
    @NotBlank(message = "contact name can't be null")
    private String contactName;
}
