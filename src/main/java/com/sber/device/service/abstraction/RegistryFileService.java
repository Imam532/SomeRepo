package com.sber.device.service.abstraction;

import com.sber.device.model.RegistryFile;

public interface RegistryFileService  {
    RegistryFile processingFile(String fileName);
    RegistryFile notProcessingFile(String fileName);
    void updateStatus(int status);
    RegistryFile geFileById(Integer id);
    void save(RegistryFile registryFile);
}
