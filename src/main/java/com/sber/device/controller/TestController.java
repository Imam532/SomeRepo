package com.sber.device.controller;

import com.sber.device.dao.abstraction.RegistryFileDao;
import com.sber.device.model.RegistryFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class TestController {
    @Autowired
    public RegistryFileDao dao;
    @GetMapping("/save")
    public void save() {
        RegistryFile registryFile = new RegistryFile("file", new Date(), 1);
        dao.save(registryFile);
    }
}
