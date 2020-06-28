package com.sber.device.service.impl;

import com.sber.device.model.Result;
import com.sber.device.model.tags.COMIS;
import com.sber.device.model.tags.RCPT;
import com.sber.device.model.tags.SERV;
import com.sber.device.service.abstraction.XmlUnmarshaller;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Service
public class XmlUnmarshallerImpl implements XmlUnmarshaller {

    /**
     * возвращает объект, результат обработки xml файла
     * посредством JAXB
     * @return Result
     */
    @Override
    public Result unmarshall(File file) throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(Result.class, RCPT.class, SERV.class, COMIS.class);
        return (Result) context.createUnmarshaller()
                .unmarshal(new FileReader(file));
    }
}
