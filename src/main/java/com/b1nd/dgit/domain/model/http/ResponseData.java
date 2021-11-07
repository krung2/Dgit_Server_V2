package com.b1nd.dgit.domain.model.http;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseData<T> extends Response {

  public T data;

  public ResponseData(HttpStatus httpStatus, String message, T data) {
    super(httpStatus, message);
    this.data = data;
  }

  public static <T> ResponseData<T> of(HttpStatus httpStatus, String message, T data) {
    return new ResponseData<>(httpStatus, message, data);
  }

  public static <T> ResponseEntity<ResponseData<T>> res(HttpStatus httpStatus, String message, T data) {
    return new ResponseEntity<>(ResponseData.of(httpStatus, message, data), httpStatus);
  }

  public static <T> ResponseEntity<ResponseData<T>> ok(String message, T data) {
    return new ResponseEntity<>(ResponseData.of(HttpStatus.OK, message, data), HttpStatus.OK);
  }
}