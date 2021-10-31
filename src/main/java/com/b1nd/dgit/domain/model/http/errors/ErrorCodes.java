package com.b1nd.dgit.domain.model.http.errors;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCodes {

  ENTITY_NOT_FOUND (HttpStatus.BAD_REQUEST, "찾지 못했습니다"),
  TOKEN_VERIFY_ERROR(HttpStatus.UNAUTHORIZED, "잘못된 토큰입니다");

  private final HttpStatus status;
  private final String message;

  ErrorCodes (HttpStatus status, String message) {
    this.status = status;
    this.message = message;
  }
}
