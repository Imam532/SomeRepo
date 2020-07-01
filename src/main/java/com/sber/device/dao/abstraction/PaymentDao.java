package com.sber.device.dao.abstraction;


import com.sber.device.model.Payment;
import com.sber.device.model.RegistryPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
@Repository
public interface PaymentDao extends CrudRepository<Payment, Integer> {

//    @Query("SELECT a FROM Payment a")
//    List<Payment> getAllConfirmedPayments();
//
//    @Query("SELECT a FROM Payment a WHERE " +
//            "a.merchant_id = :#{#record.merchant_code} AND " +
//            "a.paysys_order_date = :#{#record.oper_date} AND " +    // payment_oper_date?
//            "a.auth_code = :#{#record.auth_code} AND " +
//            "a.card_num = :#{#record.card_num} AND " +
//            "a.amount_micros = :#{#record.oper_sum}")
//    Payment findPayment(@Param("record") RegistryPayment record);

    @Query("select payments from Payment payments where payments.merchant_id = ?1 and " +
            "payments.paysys_order_date = ?2 and " +
            "payments.auth_code = ?3 and " +
            "payments.card_num = ?4 and " +
            "payments.bundle_id = (select bundle from Bundle bundle where bundle.amount = ?5)")
    Payment getPayment(String merchant_code,
                       Date oper_date,
                       String auth_code,
                       String card_num,
                       long amount_micros);

}
