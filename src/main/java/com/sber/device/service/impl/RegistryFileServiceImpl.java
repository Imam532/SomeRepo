package com.sber.device.service.impl;

import com.sber.device.dao.abstraction.RegistryFileDao;
import com.sber.device.model.RegistryFile;
import com.sber.device.service.abstraction.RegistryFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RegistryFileServiceImpl implements RegistryFileService {

    private final RegistryFileDao registryFileDao;

    @Autowired
    public RegistryFileServiceImpl(RegistryFileDao registryFileDao) {
        this.registryFileDao = registryFileDao;
    }


    @Override
    public RegistryFile processingFile(String fileName) {
        return registryFileDao.getRegistryFileByReg_file_nameAndStatus(fileName);
    }

    @Override
    public RegistryFile notProcessingFile(String fileName) {
        return registryFileDao.getRegistryFileByReg_file_name(fileName);
    }

    @Override
    public void updateStatus(int status) {
        registryFileDao.updateStatus(status);
    }

    @Override
    public RegistryFile geFileById(Integer id) {
        return registryFileDao.findById(id).get();
    }

    @Override
    public void save(RegistryFile registryFile) {
        registryFileDao.save(registryFile);
    }
}
