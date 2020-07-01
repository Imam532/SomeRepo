package com.sber.device.service.impl;

import com.sber.device.service.abstraction.MailSenderService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class MailSenderServiceImplTest {

    @Autowired
    private MailSenderService mailSenderService;


    @Test
    void sendEmail() {
        mailSenderService.sendEmail();
    }
        @Test
    void sendEmailWithAttachment() throws IOException, MessagingException {
        String path = "src/main/uploads/failed/failFile.csv";
        mailSenderService.sendEmailWithAttachment(path);
    }
}