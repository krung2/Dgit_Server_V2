package com.b1nd.dgit.domain.model.http;

import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class Response {

  public int status;
  public String message;

  public Response (HttpStatus httpStatus, String message) {
    this.status = httpStatus.value();
    this.message = message;
  }

  public static Response of (HttpStatus httpStatus, String message) {
    return new Response(httpStatus, message);
  }
}