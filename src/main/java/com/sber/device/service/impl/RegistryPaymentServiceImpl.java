package com.sber.device.service.impl;


import com.sber.device.dao.abstraction.RegistryPaymentDao;
import com.sber.device.model.Payment;
import com.sber.device.model.RegistryFile;
import com.sber.device.model.RegistryPayment;
import com.sber.device.service.abstraction.RegistryPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RegistryPaymentServiceImpl implements RegistryPaymentService {

    private final RegistryPaymentDao registryPaymentDao;

    @Autowired
    public RegistryPaymentServiceImpl(RegistryPaymentDao registryPaymentDao) {
        this.registryPaymentDao = registryPaymentDao;
    }

    @Override
    public List<RegistryPayment> findByRegFile(RegistryFile registryFile) {
        return registryPaymentDao.findAllByReg_file_id(registryFile);
    }

    @Override
    public void save(RegistryPayment registryPayment) {
        registryPaymentDao.save(registryPayment);
    }

    @Override
    public void updateMerchantCode(String merchantCode) {
        registryPaymentDao.updateMerchantCode(merchantCode);
    }

    @Override
    public void updatePaymentId(Payment payment) {
        registryPaymentDao.updatePaymentId(payment);
    }
}
