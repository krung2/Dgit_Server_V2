package com.b1nd.dgit.domain.model.http.errors;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCodes {

  ENTITY_NOT_FOUND(HttpStatus.BAD_REQUEST, "찾지 못했습니다"),
  RESOURCE_NOT_AVAILABLE(HttpStatus.GONE, "찾을 수 없는 콘텐츠 입니다"),

  TOKEN_VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "검증 오류"),
  TOKEN_FORGED_ERROR(HttpStatus.UNAUTHORIZED, "위조된 토큰입니다"),

  INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류"),
  REST_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "dauth서버 오류")
  ;

  private final HttpStatus status;
  private final String message;

  ErrorCodes (HttpStatus status, String message) {
    this.status = status;
    this.message = message;
  }
}
