package com.b1nd.dgit.domain.dto.auth;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class DodamLoginDto {

  @NotBlank(message = "code는 필수 입력값입니다")
  private String code;

  public DodamLoginDto (String code) {
    this.code = code;
  }
}
