package com.sakthivel.cmsbackend.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class MailService {

    private final JavaMailSender mailSender;

    private MailService(@Autowired JavaMailSenderImpl mailSender) {
        this.mailSender = mailSender;
    }

    public void senMail(String email, String subject, String mailBody) throws MessagingException, IOException {

        String html = Files.readString(Path.of("src/main/resources/templates/mail-response.html"));
        html = html.replace("{{body}}", mailBody);

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");

        helper.setTo(email);
        helper.setSubject(subject);
        helper.setText(html, true);

        mailSender.send(helper.getMimeMessage());
    }
}