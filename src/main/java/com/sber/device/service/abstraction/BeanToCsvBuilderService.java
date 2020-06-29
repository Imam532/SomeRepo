package com.sber.device.service.abstraction;


import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.sber.device.model.RegistryPayment;
import org.apache.poi.ss.formula.functions.T;

import java.io.IOException;
import java.util.List;

public interface BeanToCsvBuilderService {
    void build(List<RegistryPayment> registryPayments, String path)
            throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException;
}
