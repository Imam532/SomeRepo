package com.sber.device.service.impl;

import com.sber.device.model.RegistryPayment;
import com.sber.device.service.abstraction.XlsxParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class XlsxParserImplTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void parseFile() {
        XlsxParser parser = new XlsxParserImpl();
        List<RegistryPayment> list = parser.parseFile(new File("src/main/uploads/77XXXXXXXX_XXXXXX_XXXXXXXXXXXXX_171209_755928.xlsx"));
        for(RegistryPayment rg : list) {
            System.out.println("<------------------------------------>");
            System.out.println(rg.toString());
        }
        assertNotNull(list);
    }
}