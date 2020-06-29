package com.sber.device.service.impl;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.sber.device.service.abstraction.FirstStageService;
import com.sber.device.service.abstraction.PrepareReconciliation;
import com.sber.device.service.abstraction.StartReconciliation;
import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.MessagingException;
import java.io.IOException;

public class StartReconciliationImpl  implements StartReconciliation {
    private final PrepareReconciliation prepare;
    private final FirstStageService firstStageService;

    @Autowired
    public StartReconciliationImpl (PrepareReconciliation prepare, FirstStageService firstStageService) {
        this.prepare = prepare;
        this.firstStageService = firstStageService;
    }
    @Override
    public boolean start() throws IOException, MessagingException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        firstStageService.startFirstStage(prepare.isReconciliationReady());
        return true;
    }
}
