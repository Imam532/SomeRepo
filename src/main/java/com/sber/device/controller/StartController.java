package com.sber.device.controller;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.sber.device.dao.abstraction.PaymentDao;
import com.sber.device.dao.abstraction.RegistryFileDao;
import com.sber.device.dao.abstraction.RegistryPaymentDao;
import com.sber.device.model.Payment;
import com.sber.device.model.RegistryFile;
import com.sber.device.model.RegistryPayment;
import com.sber.device.service.abstraction.StartReconciliation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class StartController {
    private final StartReconciliation startReconciliation;

    @Autowired
    public RegistryFileDao dao;
    @Autowired
    public RegistryPaymentDao paymentDao;
    @Autowired
    public PaymentDao payDao;

    @Autowired
    public StartController (StartReconciliation startReconciliation) {
        this.startReconciliation = startReconciliation;
    }

    @GetMapping("/start")
    public boolean starting() throws MessagingException, CsvException, IOException, ParseException {
      return   startReconciliation.start();
    }

//    @GetMapping("/reg")
//    public List<Payment> getRP() {
//        RegistryFile registryFile = dao.findById(3).get();
//
//        List<RegistryPayment> list = paymentDao.findAllByReg_file_id(registryFile);
//
//        RegistryPayment registryPayment = list.get(0);
//        Payment payment = payDao.getPayment(registryPayment.getMerchant_code(),
//                registryPayment.getOper_date(),
//                registryPayment.getAuth_code(),
//                registryPayment.getCard_num()/*,
//                registryPayment.getPayment_order_sum()*/);
//        if(payment == null) {
//            payment = payDao.findById(1).get();
//        }
//        System.out.println(payment.getMerchant_id().equals(registryPayment.getMerchant_code()));
//        System.out.println(payment.getAuth_code().equals(registryPayment.getAuth_code()));
//        System.out.println(payment.getCard_num().equals(registryPayment.getCard_num()));
//        BigDecimal pay1 = payment.getAmount_micros();
//        BigDecimal pay2 = registryPayment.getOper_sum();
//        System.out.println(pay1.equals(pay2));
//        Date s = registryPayment.getOper_date();
//        Date s2 = payment.getPaysys_order_date();
//        System.out.println(s.equals(s2));
//
//        System.out.println(s + " : " + s2);
//        return null;
//    }

}
