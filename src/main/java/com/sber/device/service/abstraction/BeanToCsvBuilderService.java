package com.sber.device.service.abstraction;


import com.sber.device.model.RegistryPayment;

import java.util.List;

public interface BeanToCsvBuilderService {
    void build(List<RegistryPayment> registryPayments, String path);
}
