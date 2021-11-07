package com.b1nd.dgit.domain.model.http;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class Response {

  public int status;
  public String message;

  public Response(HttpStatus httpStatus, String message) {
    this.status = httpStatus.value();
    this.message = message;
  }

  public static Response of(HttpStatus httpStatus, String message) {
    return new Response(httpStatus, message);
  }

  public static ResponseEntity<Response> res(HttpStatus httpStatus, String message) {
    return new ResponseEntity<>(Response.of(httpStatus, message), httpStatus);
  }

  public static ResponseEntity<Response> ok(String message) {
    return new ResponseEntity<>(Response.of(HttpStatus.OK, message), HttpStatus.OK);
  }
}