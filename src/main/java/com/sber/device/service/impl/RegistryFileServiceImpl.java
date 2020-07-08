package com.sber.device.service.impl;

import com.sber.device.dao.abstraction.RegistryFileDao;
import com.sber.device.model.RegistryFile;
import com.sber.device.service.abstraction.RegistryFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RegistryFileServiceImpl implements RegistryFileService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final RegistryFileDao registryFileDao;

    @Autowired
    public RegistryFileServiceImpl(RegistryFileDao registryFileDao) {
        this.registryFileDao = registryFileDao;
    }


    @Override
    public RegistryFile processingFile(String fileName) {
        logger.trace("Get RegistryFile with filename and status=1. File already processed");
        return registryFileDao.getRegistryFileByReg_file_nameAndStatus(fileName);
    }

    @Override
    public RegistryFile notProcessingFile(String fileName) {
        logger.trace("Get RegistryFile with filename and status=0. File NOT processed yet");
        return registryFileDao.getRegistryFileByReg_file_name(fileName);
    }

    @Override
    public void updateStatus(int status) {
        logger.trace("Updating status. File processed successfully");
        registryFileDao.updateStatus(status);
    }

    @Override
    public RegistryFile geFileById(Integer id) {
        logger.trace("Get RegistryFile from database by id");
        return registryFileDao.findById(id).get();
    }

    @Override
    public void save(RegistryFile registryFile) {
        logger.trace("Persisting RegistryFile to database");
        registryFileDao.save(registryFile);
    }
}
