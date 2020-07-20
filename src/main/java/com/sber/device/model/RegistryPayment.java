package com.sber.device.model;


import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "registry_payments", schema ="payment")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RegistryPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = RegistryFile.class)
    @JoinColumn(name = "reg_file_id")
    private RegistryFile reg_file_id;

    @OneToOne
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    private Payment payment_id;

    //    @Column(length = 14)
    private Long merchant_code;

    //    @Column(length = 8)
    private String terminal;

    @Temporal(TemporalType.TIMESTAMP)
    private Date oper_date;

    private Integer payment_order_num;

    @Temporal(TemporalType.TIMESTAMP)
    private Date payment_oper_date;

    private Long oper_sum;

    private Long comiss_sum;

    private Long payment_order_sum;

    //    @Column(length = 21)
    private String card_num;

    //    @Column(length = 6)
    private String auth_code;

    //    @Column(length = 50)
    private String oper_type;
}
