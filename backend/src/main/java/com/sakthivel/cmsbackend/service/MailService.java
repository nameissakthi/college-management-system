package com.sakthivel.cmsbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    private final JavaMailSender mailSender;

    private MailService(@Autowired JavaMailSenderImpl mailSender) {
        this.mailSender = mailSender;
    }

    public void senMail(String email, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("sakthiveldkodi@gmail.com");
        message.setTo(email);
        message.setSubject(subject);
        message.setText(text);

        mailSender.send(message);
    }
}