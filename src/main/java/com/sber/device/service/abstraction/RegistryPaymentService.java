package com.sber.device.service.abstraction;

import com.sber.device.model.RegistryPayment;

import java.util.List;

public interface RegistryPaymentService {

    List<RegistryPayment> findByRegFileId(int regFileId);

    void save(RegistryPayment registryPayment);

    void updateMerchantCode(String merchantCode);
}
