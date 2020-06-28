package com.sber.device.service.abstraction;



import com.sber.device.model.Result;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;

public interface XmlUnmarshaller {
//    Object marshall(Object o);
    Result unmarshall(File file) throws JAXBException, IOException;
}
