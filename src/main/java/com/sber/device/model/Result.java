package com.sber.device.model;

import com.sber.device.model.tags.COMIS;
import com.sber.device.model.tags.RCPT;
import com.sber.device.model.tags.SERV;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
@XmlRootElement(name = "RESULT")
@XmlAccessorType(XmlAccessType.FIELD)
public class Result {

    @XmlElementWrapper(name = "RCPT")
    private List<RCPT> RCP;

    @XmlElementWrapper(name = "SERV")
    private List<SERV> SRV;

    @XmlElementWrapper(name = "COMIS")
    private List<COMIS> CMS;
}
