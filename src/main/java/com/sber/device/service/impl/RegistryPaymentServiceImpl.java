package com.sber.device.service.impl;


import com.sber.device.dao.abstraction.RegistryPaymentDao;
import com.sber.device.model.Payment;
import com.sber.device.model.RegistryFile;
import com.sber.device.model.RegistryPayment;
import com.sber.device.service.abstraction.RegistryPaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RegistryPaymentServiceImpl implements RegistryPaymentService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final RegistryPaymentDao registryPaymentDao;

    @Autowired
    public RegistryPaymentServiceImpl(RegistryPaymentDao registryPaymentDao) {
        this.registryPaymentDao = registryPaymentDao;
    }

    @Override
    public List<RegistryPayment> findByRegFile(RegistryFile registryFile) {
        logger.trace("Get all RegistryPayments by RegistryFile from database");
        return registryPaymentDao.findAllByReg_file_id(registryFile);
    }

    @Override
    public void save(RegistryPayment registryPayment) {
        logger.trace("Persisting RegistryPayment to database");
        registryPaymentDao.save(registryPayment);
    }

    @Override
    public void updateMerchantCode(String merchantCode) {
        logger.trace("Updating RegistryPayments's merchant_code");
        registryPaymentDao.updateMerchantCode(merchantCode);
    }

    @Override
    public void updatePaymentId(Payment payment) {
        logger.trace("Updating RegistryPayment's payment_id");
        registryPaymentDao.updatePaymentId(payment);
    }
}
