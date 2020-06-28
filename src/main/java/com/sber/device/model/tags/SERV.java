package com.sber.device.model.tags;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlType(name = "SRV")
@XmlAccessorType(XmlAccessType.FIELD)
public class SERV {     //данные по получателям
    @XmlAttribute(name = "sid")
    private String sid;     //Номер мерчанта из реестра платежей
    @XmlAttribute(name = "nam")
    private String nam;     //Название мерча
    @XmlAttribute(name = "srid")
    private String srid;    //код платежных реквизитов из раздела RCPT

}
