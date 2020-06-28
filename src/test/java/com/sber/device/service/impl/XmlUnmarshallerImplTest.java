package com.sber.device.service.impl;

import com.sber.device.model.Result;
import com.sber.device.service.abstraction.XmlUnmarshaller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class XmlUnmarshallerImplTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void unmarshall() {
        Result result = null;
        XmlUnmarshaller unmarshaller = new XmlUnmarshallerImpl();
        try {
            result = unmarshaller.unmarshall(new File("src/main/uploads/bank_r_20200501.txt"));
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertNotNull(result);
    }
}