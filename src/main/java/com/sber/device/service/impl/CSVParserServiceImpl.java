package com.sber.device.service.impl;


import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.sber.device.model.RegistryPayment;
import com.sber.device.service.abstraction.CSVParserService;
import com.sber.device.service.abstraction.ConvertXLSXToCSVService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static java.lang.Long.parseLong;

@Service
public class CSVParserServiceImpl implements CSVParserService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ConvertXLSXToCSVService converter;

    @Autowired
    public CSVParserServiceImpl(ConvertXLSXToCSVService converter) {
        this.converter = converter;
    }

    /**
     * возвращает List объектов, результат обработки CVS файла
     * посредством openCVS
     *
     * @param xlsxFile
     * @return List
     */

    @Override
    public List<RegistryPayment> parseFile(File xlsxFile) {
        logger.trace("Parse xlsx file format to beans of list RegistryPayment's");
        List<RegistryPayment> registryPayments = new ArrayList<>();
        File csvFile = converter.convertXLXSFileToCSV(xlsxFile);
        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader(csvFile));
        } catch (FileNotFoundException e) {
            logger.error("Error while reading file", e);
            e.printStackTrace();
        }
        //Read all rows at once
        List<String[]> allRows = null;
        try {
            allRows = reader.readAll();
        } catch (CsvException | IOException e) {
            logger.error("Error while CVSReader covert row to string array", e);
            e.printStackTrace();
        }

        int a = allRows.size();
        for (int i = 1; i <= allRows.size() - 1; i++) {
            RegistryPayment bean = new RegistryPayment();
            String[] row = allRows.get(i);

            if (row != null) {
                bean.setMerchant_code(Integer.parseInt(row[0]));
                bean.setTerminal(row[1]);
                try {
                    bean.setOper_date(
                            (new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH).parse(row[2])));
                    bean.setPayment_oper_date(
                            (new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH).parse(row[4])));
                } catch (ParseException e) {
                    logger.error("Error while formatting Date");
                    e.printStackTrace();
                }
                bean.setPayment_order_num(Integer.parseInt(row[3]));
                bean.setOper_sum(parseNumber(row[5]));
                bean.setComiss_sum(parseNumber(row[6]));
                bean.setPayment_order_sum(parseNumber(row[7]));
                bean.setCard_num(row[8]);
                bean.setAuth_code(row[9]);
                if (row.length == 11) {
                    bean.setOper_type(row[10]);
                } else bean.setOper_type("");
            }
            registryPayments.add(bean);
        }
        logger.trace("List of RegistryPayments created successfully");
        return registryPayments;
    }

    private Long parseNumber(String number) {
        Long l = parseLong(number.replaceAll("[^0-9?!\\-]", ""));
        return l;
    }
}
