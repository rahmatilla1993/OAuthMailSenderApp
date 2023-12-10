package com.example.oauthmailsenderapp.service;

import com.example.oauthmailsenderapp.exception.SendMessageException;
import com.example.oauthmailsenderapp.payload.ApiResponse;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

@Service
public class SendMessageService {

    private final JavaMailSender mailSender;
    @Value("${image.path}")
    private String image;

    @Autowired
    public SendMessageService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public ApiResponse send(String from, String to, String name) throws IOException {
        MimeMessage message = mailSender.createMimeMessage();
        String content = "User %s with email %s successfully registered system".formatted(name, to);
        String contentType = "text/html; charset=utf-8";
        try {
            message.setFrom(from);
            message.setRecipients(MimeMessage.RecipientType.TO, to);
            message.setSubject("Authorization success");
            message.setContent(String.format("""
                            <div>
                            <h3>%s</h3>
                            <img src="data:image/jpg;base64,%s" />
                            </div>
                            """,
                    content, getImageAsBase64()), contentType);
            mailSender.send(message);
            return new ApiResponse("Message sent successfully!", HttpStatus.OK.value());
        } catch (MessagingException e) {
            throw new SendMessageException("Error sending message: %s".formatted(e.getMessage()));
        }
    }

    private String getImageAsBase64() throws IOException {
        Path imagePath = Path.of(image, "nature7.jpg");
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(Files.readAllBytes(imagePath));
    }
}
