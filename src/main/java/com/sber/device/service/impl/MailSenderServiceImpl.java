package com.sber.device.service.impl;

import com.sber.device.service.abstraction.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;

@Service
public class MailSenderServiceImpl implements MailSenderService {

    private final JavaMailSender javaMailSender;

    @Autowired
    public MailSenderServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    @Override
    public void sendEmail() {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("imam532@mail.ru");

        msg.setSubject("Результат сверки");
        msg.setText("Сверка прошла успешно.");
        msg.setFrom("mailsendertest532@gmail.com");
        javaMailSender.send(msg);

    }

    public void sendEmailWithAttachment(String path) throws MessagingException, IOException {

        MimeMessage msg = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(msg, true);

        helper.setFrom("mailsendertest532@gmail.com");
        helper.setTo("imam532@mail.ru");
        helper.setSubject("Результат сверки");
        helper.setText("Ошибка сверки, проверьте вложенный файл");

        FileSystemResource file = new FileSystemResource(new File(path));
        helper.addAttachment("failFail.alt", file);

        javaMailSender.send(msg);

    }
}
