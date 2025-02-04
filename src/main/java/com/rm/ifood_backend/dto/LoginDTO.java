package com.rm.ifood_backend.dto;

import com.rm.ifood_backend.enums.UserType;
import lombok.Data;

@Data
public class LoginDTO {
  private String email;
  private String password;
  private UserType userType;
}
