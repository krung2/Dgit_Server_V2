package com.b1nd.dgit.domain.ro.auth;

import com.b1nd.dgit.domain.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginRo {

  private User user;
  private String token;
  private String refreshToken;
}
