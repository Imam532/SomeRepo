package com.sber.device.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "payments", schema ="payment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer merchant_id;        //compose key(ck)
    private Integer invoice_id;
    private Integer card_id;            //ck


    private Integer status;
    private String paysys_order_id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiration;
    @Temporal(TemporalType.TIMESTAMP)
    private Date autocompletion_date;

    @OneToOne(fetch = FetchType.EAGER)
    @MapsId("id")
    private Bundle bundle;          //ck
    @Temporal(TemporalType.TIMESTAMP)
    private Date paysys_order_date;     //ck
    private String ip;
    private Integer bank_id;
    private Integer card_ps_id;
    private String expired;
    private String holder;
    private String visualname;

}
