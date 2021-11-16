package com.b1nd.dgit.enums.endpoint;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum DodamEndPoint {
  DODAM_AUTH("http://dauth.b1nd.com/api"),
  DODAM_OPENAPI("http://open.dodam.b1nd.com/api");

  private String endpoint;
}
