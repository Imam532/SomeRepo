package com.sber.device.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "registry_payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RegistryPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "reg_file_id", referencedColumnName = "id")
    private RegistryFile reg_file_id;

    @OneToOne
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    private Payment payment_id;

    @Column(length = 14)
    private String merchant_code;

    @Column(length = 8)
    private String terminal;

    private int payment_order_num;
    @Temporal(TemporalType.DATE)
    private Date oper_date;
    @Temporal(TemporalType.DATE)
    private Date payment_oper_date;
    private BigDecimal oper_sum;
    private BigDecimal comiss_sum;
    private BigDecimal payment_order_sum;

    @Column(length = 21)
    private String card_num;

    //    @Column(length = 6)
    private String auth_code;

    @Column(length = 50)
    private String oper_type;
}
