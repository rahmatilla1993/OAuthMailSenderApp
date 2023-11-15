package com.example.oauthmailsenderapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return
                http
                        .csrf()
                        .disable()
                        .authorizeHttpRequests()
                        .requestMatchers("/api/home")
                        .permitAll()
                        .anyRequest()
                        .fullyAuthenticated()
                        .and()
                        .oauth2Login(withDefaults())
                        .build();
    }
}
