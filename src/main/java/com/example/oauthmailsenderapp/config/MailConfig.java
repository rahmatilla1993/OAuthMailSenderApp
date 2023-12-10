package com.example.oauthmailsenderapp.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

import static com.example.oauthmailsenderapp.util.MailConstants.*;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "mailing")
public class MailConfig {

    private String transportProtocol;
    private boolean smtpAuth;
    private boolean smtpStarttlsEnable;
    private String debug;
    private String username;
    private String password;
    private String host;
    private int port;

    @Bean
    public JavaMailSender getJavaMailSender() {
        var mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(port);

        mailSender.setUsername(username);
        mailSender.setPassword(password);

        Properties props = mailSender.getJavaMailProperties();
        props.put(MAIL_TRANSPORT_PROTOCOL, transportProtocol);
        props.put(MAIL_SMTP_AUTH, smtpAuth);
        props.put(MAIL_SMTP_STARTTLS_ENABLE, smtpStarttlsEnable);
        props.put(MAIL_DEBUG, debug);
        return mailSender;
    }

}
