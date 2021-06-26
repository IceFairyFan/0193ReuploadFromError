package com.application.posapplication.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "View not reached.")
public class OrderNotFoundException extends RuntimeException{
}
