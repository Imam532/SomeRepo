package com.sber.device.service.abstraction;

import com.opencsv.exceptions.CsvException;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface PrepareReconciliation {
    List<Integer> isReconciliationReady();
}
