package com.sber.device.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "bundle")
@Data
public class Bundle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private long amount;
    private String currency;
}
