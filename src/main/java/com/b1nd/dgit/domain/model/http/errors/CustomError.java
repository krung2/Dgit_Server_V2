package com.b1nd.dgit.domain.model.http.errors;

public class CustomError extends RuntimeException {

  private final ErrorCodes errorCodes;

  public CustomError(String message, ErrorCodes errorCode) {
    super(message);
    this.errorCodes = errorCode;
  }

  public CustomError(ErrorCodes errorCode) {
    super(errorCode.getMessage());
    this.errorCodes = errorCode;
  }

  public static CustomError of (ErrorCodes errorCodes) {
    return new CustomError(errorCodes);
  }

  public ErrorCodes getErrorCode() {
    return errorCodes;
  }
}
