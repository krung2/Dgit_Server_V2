package com.b1nd.dgit.domain.ro.auth;

import com.b1nd.dgit.domain.entities.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRo {

  private User user;
  private String token;
  private String refreshToken;

  @Builder
  public LoginRo(User user, String token, String refreshToken) {
    this.user = user;
    this.token = token;
    this.refreshToken = refreshToken;
  }
}