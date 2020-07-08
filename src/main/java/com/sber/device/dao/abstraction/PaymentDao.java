package com.sber.device.dao.abstraction;


import com.sber.device.model.Payment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PaymentDao extends CrudRepository<Payment, Integer> {

    @Query(value = "select p from Payment p join p.bundle b " +
            "where p.merchant_id = ?1 and " +
            "p.paysys_order_date = ?2 and " +
            "b.amount = ?3")
    Payment getPayment(Integer merchant_id, Date oper_date, Long amount);
}
