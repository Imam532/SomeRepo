package com.sber.device.service.abstraction;

import com.sber.device.model.Payment;
import com.sber.device.model.RegistryFile;
import com.sber.device.model.RegistryPayment;

import java.util.List;

public interface RegistryPaymentService {

    List<RegistryPayment> findByRegFile(RegistryFile registryFile);

    void save(RegistryPayment registryPayment);

    void updateMerchantCode(String merchantCode);

    void updatePaymentId(Payment payment);
}
