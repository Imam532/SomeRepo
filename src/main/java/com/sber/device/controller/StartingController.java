package com.sber.device.controller;

import com.sber.device.model.RegistryFile;
import com.sber.device.service.abstraction.StartReconciliation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
public class StartingController {
    private final StartReconciliation starting;

    @Autowired
    public StartingController(StartReconciliation starting) {
        this.starting = starting;
    }

    @GetMapping("/start")
    public List<Integer> isReady() throws IOException {
        return starting.isReconciliationReady();
    }

    @GetMapping("/files")
    public List<RegistryFile> getProcessingFiles() {
        return null;
    }

    @PostMapping("/save/")
    public void save() {
        RegistryFile registryFile = new RegistryFile("file", new Date(), 1);

    }
}
