package com.b1nd.dgit.domain.model.http;

import org.springframework.http.HttpStatus;

public class ResponseData<T> extends Response {

  public T data;

  public ResponseData (HttpStatus httpStatus, String message, T data) {
    super(httpStatus, message);
    this.data = data;
  }

  public static <T> ResponseData<T> of (HttpStatus httpStatus, String message, T data) {
    return new ResponseData<>(httpStatus, message, data);
  }
}