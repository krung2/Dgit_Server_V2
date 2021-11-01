package com.b1nd.dgit.domain.dto.dodam;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DauthRequestDto {

  private String code;
  private String clientId;
  private String clientSecret;
}
