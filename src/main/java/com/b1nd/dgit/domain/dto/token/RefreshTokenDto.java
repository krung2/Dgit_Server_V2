package com.b1nd.dgit.domain.dto.token;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class RefreshTokenDto {

  @NotBlank(message = "githubIdsms 필수 입력값입니다")
  private String refreshToken;

  public RefreshTokenDto (String refreshToken) {
    this.refreshToken = refreshToken;
  }
}