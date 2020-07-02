package com.sber.device.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
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
    private int merchant_id;        //compose key(ck)
    private int invoice_id;
    private int card_id;            //ck


    private int status;
    private String paysys_order_id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiration;
    @Temporal(TemporalType.TIMESTAMP)
    private Date autocompletion_date;
    @OneToOne
    @JoinColumn(name = "bundle_id", referencedColumnName = "id")
    private Bundle bundle_id;          //ck
    @Temporal(TemporalType.TIMESTAMP)
    private Date paysys_order_date;     //ck
    private String ip;
    private int bank_id;
    private int card_ps_id;
    private String expired;
    private String holder;
    private String visualname;

}
