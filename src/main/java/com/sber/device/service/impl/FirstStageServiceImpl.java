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
    private List<RegistryPayment> failedPayment = new ArrayList<>();
    private List<RegistryPayment> forACfile = new ArrayList<>();

    private final PaymentService paymentService;
    private final MailSenderService mailSenderService;
    private final RegistryFileService registryFileService;
    private final RegistryPaymentService registryPaymentService;
    private final BeanToCsvBuilderService beanToCsvBuilderService;

    @Autowired
    public FirstStageServiceImpl(PaymentService paymentService,
                                 MailSenderService mailSenderService,
                                 RegistryFileService registryFileService,
                                 RegistryPaymentService registryPaymentService,
                                 BeanToCsvBuilderService beanToCsvBuilderService) {
        this.paymentService = paymentService;
        this.mailSenderService = mailSenderService;
        this.registryFileService = registryFileService;
        this.registryPaymentService = registryPaymentService;
        this.beanToCsvBuilderService = beanToCsvBuilderService;
    }

    /**
     * Первый этап сверки
     * @param regFileIds
     * @throws CsvRequiredFieldEmptyException
     * @throws IOException
     * @throws CsvDataTypeMismatchException
     * @throws MessagingException
     */
    @Override
    public void startFirstStage(List<Integer> regFileIds) throws CsvRequiredFieldEmptyException, IOException, CsvDataTypeMismatchException, MessagingException {
        failedPayment = new ArrayList<>();
        forACfile = new ArrayList<>();
//        for(Integer id : regFileIds) {               //RegistryFile id -> list RegistryPayment
//            List<RegistryPayment> registryPayments = registryPaymentService.findByRegFile(registryFileService.geFileById(id));
//            for(RegistryPayment registryPayment: registryPayments) {
//                Payment payment = paymentService.findPayment(registryPayment);    //  RegistryPayment have Payment?
//                registryPaymentService.updatePaymentId(payment);                  //RegistryPayment.payment_id = Payment
//                if(payment == null) {                                            // Payment not found -> add RegistryPayment into fail list
//                    failFlag = true;
//                    registryPaymentService.updateMerchantCode(SBER_DEVICE_CODE);
//                    failedPayment.add(registryPayment);                     // only failed RegistryPayment for bank manager
//                    forACfile.add(registryPayment);                       //AC file include all RegistryPayment
//                } else {                                                  //RegistryPayment has Payment -> write success list
//                    forACfile.add(registryPayment);
//                }
//            }
//        }
//
//        processByFlag(failFlag);
//        registryFileService.updateStatus(1); // when end process mark RegistryFile
    }

    private void processByFlag(boolean failFlag) throws CsvRequiredFieldEmptyException, IOException, CsvDataTypeMismatchException, MessagingException {
        if(failFlag) {
            beanToCsvBuilderService.build(failedPayment, failPath);          // forming file *.csv with failed reconciliation
            mailSenderService.sendEmailWithAttachment(failPath);         //format .alt formed by MailSenderService during send mail

        } else {
            beanToCsvBuilderService.build(forACfile, forACPath);          // forming file *.csv for "AC расчетные решения"
            mailSenderService.sendEmail();                    //send mail  if reconciliation success
        }
    }
}
