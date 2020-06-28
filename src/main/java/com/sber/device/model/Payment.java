package com.sber.device.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String merchant_id; //TODO in base has integer --fk3
    private int invoice_id; //fk1
    private int card_id; //fk2
    private BigDecimal amount_micros;   //TODO in base has integer

    @Column(length = 5)
    private String currency;

    private short status;

    @Column(length = 50)
    private String paysys_order_id;
    @Temporal(TemporalType.DATE)
    private Date created_at;
    @Temporal(TemporalType.DATE)
    private Date paysys_order_date;

    @Column(length = 6)
    private char auth_code;

    @Column(length = 21)
    private String card_num;
    private String payment_url;
    @Temporal(TemporalType.DATE)
    private Date expiration;
    private String uid;
}
