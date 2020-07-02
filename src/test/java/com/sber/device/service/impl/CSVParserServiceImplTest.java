package com.sber.device.service.impl;

import com.opencsv.exceptions.CsvException;
import com.sber.device.model.RegistryPayment;
import com.sber.device.service.abstraction.CSVParserService;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class CSVParserServiceImplTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void parseFile() throws IOException, ParseException, CsvException {
        CSVParserService parser = new CSVParserServiceImpl(new ConvertXLSXToCSVServiceImpl());
        List<RegistryPayment> beans =  parser.parseFile(new File("convertedCSVFile.csv"));
        System.out.println(beans.toString());
    }
}