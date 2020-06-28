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
@XmlType(name = "CMS")
@XmlAccessorType(XmlAccessType.FIELD)
public class COMIS {        //данные по комиссии каждого получателя
    @XmlAttribute(name = "rid")
    private String rid;        //уник индетификатор реквезитов в рамках 1 файла из <RCPT>  *int !!!IMPORTANT
    @XmlAttribute(name = "sid")
    private String sid;     //код услуги для разчета коммиссии из <SERV>
    @XmlAttribute(name = "cid")
    private String cid;     //код настройки комиссии
    @XmlAttribute(name = "typ")
    private String typ;        //тип комиссии  *int !!!IMPORTANT
    @XmlAttribute(name = "pct")
    private String pct;        //% комиссии   *int !!!IMPORTANT
//    @XmlAttribute(name = "pen")
//    private int pen;        //пении (1- пении по услуге, 0 - нет)
    @XmlAttribute(name = "rfd")
    private String rfd;       //начало периода настройки;   *Date !!!IMPORTANT
    @XmlAttribute(name = "rtf")
    private String rtf;       //окончание периода настройки   *Date !!!IMPORTANT
//    @XmlAttribute(name = "hld")
//    private String hld;     //коды услуг через запятую, с которых удерживается комиссия
}
