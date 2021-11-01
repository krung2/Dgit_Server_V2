package com.b1nd.dgit.domain.dto.dodam;

public class RestBaseDto {

  private final int status;
  private final String message;

  public RestBaseDto (int status, String message) {
    this.status = status;
    this.message = message;
  }
}
