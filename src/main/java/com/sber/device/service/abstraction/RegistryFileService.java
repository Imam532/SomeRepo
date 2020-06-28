package com.sber.device.service.abstraction;

import com.sber.device.model.RegistryFile;

public interface RegistryFileService  {
    RegistryFile processingFile(String fileName);
    RegistryFile getFileByName(String fileName);
    void save(RegistryFile registryFile);
}
