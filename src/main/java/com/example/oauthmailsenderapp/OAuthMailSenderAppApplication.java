package com.example.oauthmailsenderapp;

import com.example.oauthmailsenderapp.config.MailConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        MailConfig.class
})
public class OAuthMailSenderAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(OAuthMailSenderAppApplication.class, args);
    }

}
