package ru.itis.services;


public interface EmailService {
    void sendMail(String text, String subject, String email);
    void sendMailWithPassword(String text, String subject, String email);
}
