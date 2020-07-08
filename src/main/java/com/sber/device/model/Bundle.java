package com.sber.device.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "bundle", schema ="payment")
@Data
public class Bundle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Long amount;
    private String currency;
}
