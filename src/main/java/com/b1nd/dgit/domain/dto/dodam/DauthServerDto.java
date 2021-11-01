package com.b1nd.dgit.domain.dto.dodam;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class DauthServerDto extends RestBaseDto {

  private final DauthServerData data;

  public DauthServerDto (int status, String message, DauthServerData data) {
    super(status, message);
    this.data = data;
  }

  @Getter
  @AllArgsConstructor
  public static class DauthServerData {
    private String access_token;
    private String refresh_token;
    private String token_type;
    private String expires_in;
  }
}
