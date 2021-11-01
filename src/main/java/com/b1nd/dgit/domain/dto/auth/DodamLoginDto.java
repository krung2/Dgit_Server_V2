package com.b1nd.dgit.domain.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class DodamLoginDto {

  @NotBlank(message = "code는 필수 입력값입니다")
  private String code;
}
