package com.b1nd.dgit.domain.dto.dodam;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
public class DauthRequestDto {

  private String code;
  private String client_id;
  private String client_secret;
}