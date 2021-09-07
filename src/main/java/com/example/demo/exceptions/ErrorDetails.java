package com.example.demo.exceptions;

import lombok.Data;

import java.util.Date;

@Data
public class ErrorDetails {

    private Date timestamp;

    private String message;

    private String detail;


    public ErrorDetails(Date timestamp, String message, String detail) {
        this.timestamp = timestamp;
        this.message = message;
        this.detail = detail;
    }
}
