package com.sber.device.service.impl;

import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.sber.device.model.RegistryPayment;
import com.sber.device.service.abstraction.BeanToCsvBuilderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

@Service
public class BeanToCsvBuilderServiceImpl implements BeanToCsvBuilderService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void build(List<RegistryPayment> registryPayments, String path) {
        try {
            logger.trace("Write cvs file from bean to directory for send mail to bank manager");
            Writer writer = new FileWriter(path);
            StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer).build();
            for (RegistryPayment registryPayment : registryPayments ) {
                beanToCsv.write(registryPayment);
            }
            writer.close();
            logger.trace("cvs fail for bank manager created successfully");
        }  catch (CsvRequiredFieldEmptyException|CsvDataTypeMismatchException ex ) {
            logger.error("Error by creating file", ex);
            ex.printStackTrace();
        } catch ( IOException ex) {
            logger.error("Error while writing cvs file to directory", ex);
            ex.getStackTrace();
        }
    }
}
