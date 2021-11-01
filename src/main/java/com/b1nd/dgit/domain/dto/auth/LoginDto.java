package com.b1nd.dgit.domain.dto.auth;

import com.b1nd.dgit.domain.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginDto {

  private User user;
  private String token;
  private String refreshToken;
}