package com.sber.device.service.impl;

import com.sber.device.service.abstraction.FirstStageService;
import com.sber.device.service.abstraction.PrepareReconciliation;
import com.sber.device.service.abstraction.StartReconciliation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StartReconciliationImpl implements StartReconciliation {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final PrepareReconciliation prepare;
    private final FirstStageService firstStageService;

    @Autowired
    public StartReconciliationImpl(PrepareReconciliation prepare, FirstStageService firstStageService) {
        this.prepare = prepare;
        this.firstStageService = firstStageService;
    }

    @Override
    public boolean start() {
        logger.trace("Starting reconciliation");
        firstStageService.startFirstStage(prepare.isReconciliationReady());
        return true;
    }
}
