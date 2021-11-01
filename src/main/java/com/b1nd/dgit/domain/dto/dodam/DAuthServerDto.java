package com.b1nd.dgit.domain.dto.dodam;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DAuthServerDto {

  private String access_token;
  private String refresh_token;
  private String token_type;
  private String expires_in;

}
