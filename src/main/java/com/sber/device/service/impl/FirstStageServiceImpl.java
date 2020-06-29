package com.sber.device.service.impl;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.sber.device.model.Payment;
import com.sber.device.model.RegistryPayment;
import com.sber.device.service.abstraction.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FirstStageServiceImpl implements FirstStageService {
    private boolean failFlag;
    private String failPath = "src/main/uploads/failed/failFile.csv";
    private String forACPath = "src/main/uploads/forAC/acFile.csv";
    private final String SBER_DEVICE_CODE = "sber_device";

    private final PaymentService paymentService;
    private final MailSenderService mailSenderService;
    private final BeanToCsvBuilderService csvBuilderService;
    private final RegistryPaymentService registryPaymentService;


    @Autowired
    public FirstStageServiceImpl(PaymentService paymentService,
                                 MailSenderService mailSenderService,
                                 BeanToCsvBuilderService csvBuilderService,
                                 RegistryPaymentService registryPaymentService) {
        this.paymentService = paymentService;
        this.mailSenderService = mailSenderService;
        this.csvBuilderService = csvBuilderService;
        this.registryPaymentService = registryPaymentService;
    }

    @Override
    public void startFirstStage(List<Integer> regFileIds) throws CsvRequiredFieldEmptyException, IOException, CsvDataTypeMismatchException, MessagingException {
        List<RegistryPayment> failedPayment = new ArrayList<>();
        List<RegistryPayment> forACfile = new ArrayList<>();
        for(Integer id : regFileIds) {               //RegistryFile id -> list RegistryPayment
            List<RegistryPayment> registryPayments = registryPaymentService.findByRegFileId(id);
            for(RegistryPayment registryPayment: registryPayments) {
                Payment payment = paymentService.findPayment(registryPayment);    //  RegistryPayment not Payment?
                if(payment == null) {                                            // Payment not found -> add RegistryPayment into fail list
                    failFlag = true;
                    registryPaymentService.updateMerchantCode(SBER_DEVICE_CODE);
                    failedPayment.add(registryPayment);                     // only failed RegistryPayment for bank manager
                    forACfile.add(registryPayment);                       //AC file include all RegistryPayment
                } else {                                                  //RegistryPayment has Payment -> write success list
                    forACfile.add(registryPayment);
                }
            }
        }

        if(failFlag) {
            csvBuilderService.build(failedPayment, failPath);          // forming file with failed reconciliation
            mailSenderService.sendEmailWithAttachment(failPath); //TODO file format *.alt

        } else {
            mailSenderService.sendEmail();                    //send mail  if reconciliation success
        }
        csvBuilderService.build(forACfile, forACPath);          // forming file for "AC расчетные решения"
    }
}
