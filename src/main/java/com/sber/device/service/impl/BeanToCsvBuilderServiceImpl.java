package com.sber.device.service.impl;

import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.sber.device.model.RegistryPayment;
import com.sber.device.service.abstraction.BeanToCsvBuilderService;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

@Service
public class BeanToCsvBuilderServiceImpl implements BeanToCsvBuilderService {

    @Override
    public void build(List<RegistryPayment> registryPayments, String path)
            throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        Writer writer = new FileWriter(path);
        StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer).build();
        for (RegistryPayment registryPayment : registryPayments ) {
            beanToCsv.write(registryPayment);
        }
        writer.close();
    }
}
