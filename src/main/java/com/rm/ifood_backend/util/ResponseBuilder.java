package com.rm.ifood_backend.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseBuilder {
  public static ResponseEntity<Map<String, Object>> builder(HttpStatus status, String message, Object data){
    Map<String, Object> responseMap = new HashMap<>();
    responseMap.put("status", status.value());
    responseMap.put("error", status.getReasonPhrase());
    responseMap.put("message", message);
    responseMap.put("data", data);
    return new ResponseEntity<>(responseMap, status);
  }
}
