package com.sber.device.service.abstraction;

import com.sber.device.model.Payment;
import com.sber.device.model.RegistryFile;
import com.sber.device.model.RegistryPayment;

import java.util.List;

public interface PaymentService {
    Payment findPayment(RegistryPayment registryPayment);
    void update(Integer status);
    List<Payment> getNotProcessedPayments();
}
