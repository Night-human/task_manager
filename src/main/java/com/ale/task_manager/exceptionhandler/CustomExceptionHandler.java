/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.ale.task_manager.exceptionhandler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ale.task_manager.customresponses.ApiResponse;
import com.ale.task_manager.exceptionhandler.custom_exceptions.CustomExceptionsMessages;
import com.ale.task_manager.exceptionhandler.custom_exceptions.TaskNotFoundException;

/**
 *
 * @author night
 */
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> notValidExceptionHandler(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(new ApiResponse<>(false, CustomExceptionsMessages.methodArgumentNotValidException, errors));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> deserializationExceptionHandler(HttpMessageNotReadableException e) {
        return ResponseEntity.badRequest().body(new ApiResponse<>(false, CustomExceptionsMessages.messageNotReadableException, e.getMessage()));
    }

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<?> taskNotFoundExceptionHandler(TaskNotFoundException e) {
        return ResponseEntity.badRequest().body(new ApiResponse<>(false, e.getMessage(), null));
    }
}
