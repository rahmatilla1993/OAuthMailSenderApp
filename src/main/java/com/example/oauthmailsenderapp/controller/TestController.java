package com.example.oauthmailsenderapp.controller;

import com.example.oauthmailsenderapp.exception.SendMessageException;
import com.example.oauthmailsenderapp.payload.ApiResponse;
import com.example.oauthmailsenderapp.service.SendMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class TestController {

    private final SendMessageService service;

    @Autowired
    public TestController(SendMessageService service) {
        this.service = service;
    }

    @GetMapping("/home")
    public HttpEntity<String> homePage() {
        return ResponseEntity.ok("Welcome to home page");
    }

    @GetMapping("/register")
    public HttpEntity<ApiResponse> secured(@AuthenticationPrincipal OAuth2User user) throws IOException {
        String name = user.getAttribute("name");
        String email = user.getAttribute("email");
        return ResponseEntity.ok(
                service.send("noreply@mail.com", email, name)
        );
    }

    @ExceptionHandler
    public HttpEntity<ApiResponse> handle(SendMessageException e) {
        return ResponseEntity
                .badRequest()
                .body(new ApiResponse(e.getMessage(),
                        HttpStatus.BAD_REQUEST.value())
                );
    }
}
