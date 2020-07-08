package com.sber.device.service.impl;

import com.sber.device.model.Result;
import com.sber.device.model.tags.COMIS;
import com.sber.device.model.tags.RCPT;
import com.sber.device.model.tags.SERV;
import com.sber.device.service.abstraction.XmlUnmarshaller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

@Service
public class XmlUnmarshallerImpl implements XmlUnmarshaller {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * возвращает объект, результат обработки xml файла
     * посредством JAXB
     * @return Result
     */
    @Override
    public Result unmarshall(File file){
        logger.trace("Parsing xml file to bean Result ");
        JAXBContext context = null;
        try {
            context = JAXBContext.newInstance(Result.class, RCPT.class, SERV.class, COMIS.class);
        } catch (JAXBException e) {
            logger.error("Error wile creating Result by JAXBContext", e);
            e.printStackTrace();
        }
        try {
            logger.trace("Result bean created successfully");
            return (Result) context.createUnmarshaller()
                    .unmarshal(new FileReader(file));
        } catch (JAXBException e) {
            logger.error("Error wile creating Result by JAXBContext", e);
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            logger.error("Not found xml file for unmarshalling");
            e.printStackTrace();
        }
        logger.warn("Result is null");
        return null;
    }
}
