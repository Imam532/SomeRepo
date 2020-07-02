package com.sber.device.service.impl;

import com.opencsv.exceptions.CsvException;
import com.sber.device.service.abstraction.CSVParserService;
import com.sber.device.service.abstraction.FileSearcher;
import com.sber.device.service.abstraction.RegistryFileService;
import com.sber.device.service.abstraction.RegistryPaymentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PrepareReconciliationImplTest {

    @Autowired
    FileSearcher fileSearcher;
    @Autowired
    CSVParserService parser;
    @Autowired
    RegistryFileService fileService;
    @Autowired
    RegistryPaymentService paymentService;
    @Test
    void isReconciliationReady() throws ParseException, IOException, CsvException {
        PrepareReconciliationImpl prepare = new PrepareReconciliationImpl(fileSearcher,parser,fileService,paymentService);
        List<Integer> ids = prepare.isReconciliationReady();
    }
}