package com.example.oauthmailsenderapp.config;

import com.example.oauthmailsenderapp.util.MailConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

import static com.example.oauthmailsenderapp.util.MailConstants.*;
import static com.example.oauthmailsenderapp.util.PropertiesUtil.get;

@Configuration
public class MailConfig {

    @Bean
    public JavaMailSender getJavaMailSender() {
        var mailSender = new JavaMailSenderImpl();
        mailSender.setHost(get(MailConstants.MAIL_HOST));
        mailSender.setPort(Integer.parseInt(get(MAIL_PORT)));

        mailSender.setUsername(get(MAIL_USERNAME));
        mailSender.setPassword(get(MAIL_PASSWORD));

        Properties props = mailSender.getJavaMailProperties();
        props.put(MAIL_TRANSPORT_PROTOCOL, get(MAIL_TRANSPORT_PROTOCOL));
        props.put(MAIL_SMTP_AUTH, get(MAIL_SMTP_AUTH));
        props.put(MAIL_SMTP_STARTTLS_ENABLE, get(MAIL_SMTP_STARTTLS_ENABLE));
        props.put(MAIL_DEBUG, get(MAIL_DEBUG));
        return mailSender;
    }

}
