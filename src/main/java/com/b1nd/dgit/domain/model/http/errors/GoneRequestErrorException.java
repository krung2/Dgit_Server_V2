package com.b1nd.dgit.domain.model.http.errors;

public class GoneRequestErrorException extends CustomError {

  public GoneRequestErrorException(ErrorCodes errorCode) {
    super(errorCode);
  }

  public static CustomError of(String message) {
    return new CustomError(message, ErrorCodes.RESOURCE_NOT_AVAILABLE);
  }
}
