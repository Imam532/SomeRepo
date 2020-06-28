package com.sber.device.dao.abstraction;


import com.sber.device.model.Payment;
import com.sber.device.model.RegistryPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PaymentDao extends CrudRepository<Payment, Integer> {


//    @Query("SELECT a FROM Payment a")
//    List<Payment> getAllConfirmedPayments();
//
    @Query("SELECT a FROM Payment a WHERE " +
            "a.merchant_id = :#{#record.merchant_code} AND " +
            "a.paysys_order_date = :#{#record.payment_oper_date} AND " +
            "a.auth_code = :#{#record.auth_code} AND " +
            "a.card_num = :#{#record.card_num} AND " +
            "a.amount_micros = :#{#record.oper_sum}")
    Payment findPayment(@Param("record") RegistryPayment record);
}
