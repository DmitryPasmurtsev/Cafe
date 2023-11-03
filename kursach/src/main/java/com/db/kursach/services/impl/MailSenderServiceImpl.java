package com.db.kursach.services.impl;

import com.db.kursach.services.MailSenderService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailSenderServiceImpl implements MailSenderService {

    private final JavaMailSender emailSender;

    public MailSenderServiceImpl(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }


    @Override
    public void sendEmail(String toAddress, String subject, String employeeFullName) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo("dima427614@gmail.com");
        simpleMailMessage.setSubject(subject);
        String text = "Уважаемый " + employeeFullName + ", вы приняты на работу в Кафейню. " +
                "Для регистрации аккаунта используйте электронную почту " + toAddress + ". Сайт Кафейни: http://localhost:8060";
        simpleMailMessage.setText(text);
        emailSender.send(simpleMailMessage);
    }
}
