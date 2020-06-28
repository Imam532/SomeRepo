package com.sber.device.service.impl;


import com.sber.device.dao.abstraction.RegistryPaymentDao;
import com.sber.device.model.RegistryPayment;
import com.sber.device.service.abstraction.RegistryPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RegistryPaymentServiceImpl implements RegistryPaymentService {

    private final RegistryPaymentDao registryPaymentDao;

    @Autowired
    public RegistryPaymentServiceImpl (RegistryPaymentDao registryPaymentDao) {
        this.registryPaymentDao = registryPaymentDao;
    }

    @Override
    public void save(RegistryPayment registryPayment) {
        registryPaymentDao.save(registryPayment);
    }
}
