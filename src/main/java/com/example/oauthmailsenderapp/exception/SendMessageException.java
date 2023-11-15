package com.example.oauthmailsenderapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SendMessageException extends RuntimeException{
    public SendMessageException(String message) {
        super(message);
    }
}
