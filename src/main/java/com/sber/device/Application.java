package com.sber.device;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;

@SpringBootApplication
@EnableScheduling
@EnableJpaAuditing
public class Application {
    public static void main(String[] args) throws IOException {

        SpringApplication.run(Application.class, args);

    }

//    public void run(String... args) {
//
//        System.out.println("Sending Email...");
//
//        try {
//            //sendEmail();
//            sendEmailWithAttachment();
//
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("Done");
//
//    }
}
