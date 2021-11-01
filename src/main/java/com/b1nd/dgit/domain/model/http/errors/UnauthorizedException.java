package com.b1nd.dgit.domain.model.http.errors;

public class UnauthorizedException extends CustomError {

  public UnauthorizedException(String errorCode) {
    super(errorCode);
  }

  public static CustomError of (String message) {
    return new CustomError(message, ErrorCodes.ENTITY_NOT_FOUND);
  }
}
