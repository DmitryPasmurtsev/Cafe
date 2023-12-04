package com.db.kursach.services.impl;

import com.db.kursach.services.MailSenderService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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
        simpleMailMessage.setTo(toAddress);
        simpleMailMessage.setSubject(subject);
        String text = "Уважаемый " + employeeFullName + ", вы приняты на работу в Кофейню. " +
                "Для регистрации аккаунта используйте электронную почту " + toAddress + ". Сайт Кофейни: http://localhost:3000";
        simpleMailMessage.setText(text);
        emailSender.send(simpleMailMessage);
    }
}
