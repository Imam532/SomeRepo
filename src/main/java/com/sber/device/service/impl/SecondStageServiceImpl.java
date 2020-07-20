package com.sber.device.service.impl;

import com.sber.device.model.Payment;
import com.sber.device.service.abstraction.PaymentService;
import com.sber.device.service.abstraction.SecondStageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SecondStageServiceImpl implements SecondStageService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final PaymentService paymentService;
    private final BeanToCsvBuilderServiceImpl beanToCsvBuilderService;

    @Autowired
    public SecondStageServiceImpl (PaymentService paymentService,
                                   BeanToCsvBuilderServiceImpl beanToCsvBuilderService) {
        this.paymentService = paymentService;
        this.beanToCsvBuilderService = beanToCsvBuilderService;
    }


    @Override
    public void startSecondStage() {
       List<Payment> listPayment = paymentService.getNotProcessedPayments();
    }
}
