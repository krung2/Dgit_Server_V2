package com.b1nd.dgit.domain.dto.token;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RefreshTokenDto {

  private String refreshToken;
}