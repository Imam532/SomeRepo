package com.sber.device.service.impl;

import com.opencsv.CSVWriter;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ConvertXLSXToCSVServiceImplTest {

    @Test
    void convertXLXSFileToCSV() throws IOException {
        ConvertXLSXToCSVServiceImpl converter = new ConvertXLSXToCSVServiceImpl();
        converter.convertXLXSFileToCSV(new File("src/main/uploads/77XXXXXXXX_XXXXXX_XXXXXXXXXXXXX_171209_755928.xlsx"));
    }
}