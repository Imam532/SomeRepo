package com.sber.device.service.impl;

import com.sber.device.service.abstraction.MailSenderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class MailSenderServiceImpl implements MailSenderService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final JavaMailSender javaMailSender;

    @Autowired
    public MailSenderServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    @Override
    public void sendEmail() {
        logger.trace("Sending mail about success reconciliation to bank manager");
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("imam532@mail.ru");

        msg.setSubject("Результат сверки");
        msg.setText("Сверка прошла успешно.");
        msg.setFrom("mailsendertest532@gmail.com");
        javaMailSender.send(msg);
        logger.trace("Send mail about success is done");
    }

    public void sendEmailWithAttachment(String path) {

        MimeMessage msg = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = null;
        try {
            logger.trace("Send mail with a failed file attachment");
            helper = new MimeMessageHelper(msg, true);
            helper.setFrom("mailsendertest532@gmail.com");
            helper.setTo("imam532@mail.ru");
            helper.setSubject("Результат сверки");
            helper.setText("Ошибка сверки, проверьте вложенный файл");

            FileSystemResource file = new FileSystemResource(new File(path));
            helper.addAttachment("failFail.alt", file);
        } catch (MessagingException e) {
            logger.error("Error while sending mail with attachment", e);
            e.printStackTrace();
        }
        javaMailSender.send(msg);
        logger.trace("Mail with attachment send successfully");

    }
}
