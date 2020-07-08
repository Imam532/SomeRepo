package com.sber.device.service.abstraction;

import com.sber.device.model.Payment;
import com.sber.device.model.RegistryFile;
import com.sber.device.model.RegistryPayment;

public interface PaymentService {
    Payment findPayment(RegistryPayment registryPayment);
}
