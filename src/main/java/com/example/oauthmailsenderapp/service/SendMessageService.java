package com.example.oauthmailsenderapp.service;

import com.example.oauthmailsenderapp.exception.SendMessageException;
import com.example.oauthmailsenderapp.payload.ApiResponse;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendMessageService {

    private final JavaMailSender mailSender;

    @Autowired
    public SendMessageService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public ApiResponse send(String from, String to, String name) {
        MimeMessage message = mailSender.createMimeMessage();
        String content = "User %s with email %s successfully registered system".formatted(name, to);
        String contentType = "text/html; charset=utf-8";
        try {
            message.setFrom(from);
            message.setRecipients(MimeMessage.RecipientType.TO, to);
            message.setSubject("Authorization success");
            message.setContent(String.format("<h3>%s</h3>", content), contentType);
            mailSender.send(message);
            return new ApiResponse("Message sent successfully!", HttpStatus.OK.value());
        } catch (MessagingException e) {
            throw new SendMessageException("Error sending message: %s".formatted(e.getMessage()));
        }
    }
}
