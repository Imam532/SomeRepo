package com.sber.device.service.impl;

import com.sber.device.dao.abstraction.PaymentDao;
import com.sber.device.model.Payment;
import com.sber.device.model.RegistryPayment;
import com.sber.device.service.abstraction.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final PaymentDao paymentDao;

    @Autowired
    public PaymentServiceImpl (PaymentDao paymentDao) {
        this.paymentDao = paymentDao;
    }

    @Override
    public Payment findPayment(RegistryPayment registryPayment) {
        logger.trace("Get Payment from database");
        return paymentDao.getPayment(
                registryPayment.getMerchant_code(),
                registryPayment.getOper_date(),
                registryPayment.getPayment_order_sum());
    }

    @Override
    public void update(Integer status) {
        logger.trace("Updating status of Payment");
        paymentDao.updateStatus(status);
    }

    @Override
    public List<Payment> getNotProcessedPayments() {
        logger.trace("Get list of Payment where not process before");
        return paymentDao.getNotProcessedPayments();
    }
}
