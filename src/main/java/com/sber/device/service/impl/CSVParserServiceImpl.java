package com.sber.device.service.impl;


import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;
import com.sber.device.model.RegistryPayment;
import com.sber.device.service.abstraction.CSVParserService;
import com.sber.device.service.abstraction.ConvertXLSXToCSVService;
import lombok.SneakyThrows;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static java.lang.Long.parseLong;
import static java.math.BigDecimal.valueOf;

@Service
public class CSVParserServiceImpl implements CSVParserService {



    private final ConvertXLSXToCSVService converter;

    @Autowired
    public CSVParserServiceImpl(ConvertXLSXToCSVService converter) {
        this.converter = converter;
    }
    /**
     * возвращает List объектов, результат обработки CVS файла
     * посредством openCVS
     * @param file
     * @return List
     */

    @Override
    public List<RegistryPayment> parseFile(File xlsxFile) throws IOException, CsvException, ParseException {
            List<RegistryPayment> registryPayments = new ArrayList<>();
            File csvFile = converter.convertXLXSFileToCSV(xlsxFile);
            CSVReader reader = new CSVReader(new FileReader(csvFile));
            //Read all rows at once
            List<String[]> allRows = reader.readAll();

            int a = allRows.size();
            for(int i = 1; i <= allRows.size()-1; i++){
                RegistryPayment bean = new RegistryPayment();
                String[] row = allRows.get(i);

                if(row != null) {
                    bean.setMerchant_code(Integer.parseInt(row[0]));
                    bean.setTerminal(row[1]);
                    bean.setOper_date(
                            (new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH).parse(row[2])));
                    bean.setPayment_order_num(Integer.parseInt(row[3]));
                    bean.setPayment_oper_date(
                            (new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH).parse(row[4])));
                    bean.setOper_sum(parseNumber(row[5]));
                    bean.setComiss_sum(parseNumber(row[6]));
                    bean.setPayment_order_sum(parseNumber(row[7]));
                    bean.setCard_num(row[8]);
                    bean.setAuth_code(row[9]);
                    if(row.length == 11) {
                        bean.setOper_type(row[10]);
                    } else bean.setOper_type("");
                }
                registryPayments.add(bean);
            }
        return registryPayments;
    }

    private Long parseNumber(String number) {
        Long l = parseLong(number.replaceAll("[^0-9?!\\-]", ""));

        return l;
    }
}
