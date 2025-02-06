package com.rm.ifood_backend.exceptions;

import com.rm.ifood_backend.util.ResponseBuilder;
import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(EntityNotFoundException.class)
  private ResponseEntity<Map<String, Object>> entityNotFoundHandler(EntityNotFoundException exception) {
    return ResponseBuilder.builder(HttpStatus.NOT_FOUND, exception.getMessage(), null);
  }

  @ExceptionHandler(Exception.class)
  private ResponseEntity<Map<String, Object>> internalServerErrorHandler(Exception exception) {
    return ResponseBuilder.builder(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(), null);
  }

  @ExceptionHandler(BadRequestException.class)
  private ResponseEntity<Map<String, Object>> badRequestErrorHandler(BadRequestException exception) {
    return ResponseBuilder.builder(HttpStatus.BAD_REQUEST, exception.getMessage(), null);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  private ResponseEntity<Map<String, Object>> validationExceptionHandler(MethodArgumentNotValidException exception) {
    List<String> errors = exception.getBindingResult().getFieldErrors().stream()
        .map(error -> error.getField() + ": " + error.getDefaultMessage())
        .collect(Collectors.toList());

    return ResponseBuilder.builder(HttpStatus.UNPROCESSABLE_ENTITY, String.join("; ", errors), null);
  }

  @ExceptionHandler(AuthenticationException.class)
  private ResponseEntity<Map<String, Object>> unauthorizedExceptionHandler(AuthenticationException exception){
    return ResponseBuilder.builder(HttpStatus.UNAUTHORIZED, exception.getMessage(), null);
  }
}
