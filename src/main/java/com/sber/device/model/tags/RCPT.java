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
@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class RCPT {       //платежные реквизиты получателей
    @XmlAttribute(name = "rid")
    private String rid;     // идентификатор *int IMPORTANT!!
    @XmlAttribute(name = "nam")
    private String nam;   //имя получателя платежа
    @XmlAttribute(name = "inn")
    private String inn;   //ИНН
    @XmlAttribute(name = "kpp")
    private String kpp;    //КПП
    @XmlAttribute(name = "acc")
    private String acc;     //расчетный счет
    @XmlAttribute(name = "bic")
    private String bic;     //БИК банка получателя
    @XmlAttribute(name = "bnk")
    private String bnk;     //название банка получателя
    @XmlAttribute(name = "corr")
    private String corr;    //корреспондетский счет
    /*
    @XmlAttribute(name = "kbk")
    private String kbk;     //код бюджетной классификации
    @XmlAttribute(name = "okt")
    private String okt;     //ОКТМО
     */
    @XmlAttribute(name = "rsn")
    private String rsn;     //назачения платежа;
}
