package com.db.kursach.services;

public interface MailSenderService {
    void sendEmail(String toAddress, String subject, String employeeFullName);
}
