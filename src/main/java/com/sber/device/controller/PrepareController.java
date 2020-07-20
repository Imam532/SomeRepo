package com.sber.device.controller;

import com.opencsv.exceptions.CsvException;
import com.sber.device.dao.abstraction.PaymentDao;
import com.sber.device.dao.abstraction.RegistryPaymentDao;
import com.sber.device.model.Payment;
import com.sber.device.model.RegistryFile;
import com.sber.device.model.RegistryPayment;
import com.sber.device.service.abstraction.PaymentService;
import com.sber.device.service.abstraction.PrepareReconciliation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@RestController
public class PrepareController {
    private final PrepareReconciliation starting;
    private final PaymentDao dao;
    private final RegistryPaymentDao rpdao;

    private final Logger logger = LoggerFactory.getLogger(PrepareController.class);
    @Autowired
    public PrepareController(PrepareReconciliation starting, PaymentDao dao,RegistryPaymentDao rpdao) {
        this.starting = starting;
        this.dao = dao;
        this.rpdao = rpdao;
    }

    @GetMapping("/prepare")
    public List<Integer> isReady() throws IOException, CsvException, ParseException {
        logger.debug("starting prepare");
        return starting.isReconciliationReady();
    }

    @GetMapping("/file")
    public Payment getPayment() {
        RegistryPayment rp = rpdao.findById(3).get();

        Payment payment = dao.getPayment(rp.getMerchant_code(),
                rp.getOper_date(), rp.getPayment_order_sum());
        System.out.println(payment.toString());
        return payment;
    }

    @PostMapping("/save/")
    public void save() {
        RegistryFile registryFile = new RegistryFile("file", new Date(), 1);
    }

    @GetMapping("/get")
    public List<Payment> getList() {
       return dao.getNotProcessedPayments();
    }
}
