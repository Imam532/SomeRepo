package com.sber.device.service.abstraction;

import javax.mail.MessagingException;
import java.io.IOException;

public interface MailSenderService {

    void sendEmail();

    void sendEmailWithAttachment(String path) throws MessagingException, IOException;

}
