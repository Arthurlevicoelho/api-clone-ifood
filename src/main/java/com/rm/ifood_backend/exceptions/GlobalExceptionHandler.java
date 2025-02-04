package com.rm.ifood_backend.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

  private ResponseEntity<Map<String, Object>> buildError(HttpStatus status, String message) {
    Map<String, Object> errorMap = new HashMap<>();
    errorMap.put("status", status.value());
    errorMap.put("error", status.getReasonPhrase());
    errorMap.put("message", message);
    return new ResponseEntity<>(errorMap, status);
  }

  @ExceptionHandler(EntityNotFoundException.class)
  private ResponseEntity<Map<String, Object>> entityNotFoundHandler(EntityNotFoundException exception) {
    return buildError(HttpStatus.NOT_FOUND, exception.getMessage());
  }

  @ExceptionHandler(Exception.class)
  private ResponseEntity<Map<String, Object>> internalServerErrorHandler(Exception exception) {
    return buildError(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
  }

  @ExceptionHandler(BadRequestException.class)
  private ResponseEntity<Map<String, Object>> badRequestErrorHandler(BadRequestException exception) {
    return buildError(HttpStatus.BAD_REQUEST, exception.getMessage());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  private ResponseEntity<Map<String, Object>> validationExceptionHandler(MethodArgumentNotValidException exception) {
    List<String> errors = exception.getBindingResult().getFieldErrors().stream()
        .map(error -> error.getField() + ": " + error.getDefaultMessage())
        .collect(Collectors.toList());

    return buildError(HttpStatus.UNPROCESSABLE_ENTITY, String.join("; ", errors));
  }
}
