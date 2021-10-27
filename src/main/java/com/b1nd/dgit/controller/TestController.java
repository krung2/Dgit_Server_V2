package com.b1nd.dgit.controller;

import github.queries.GetContributionQuery;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Nullable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TestController {

  private final AService aService;

  @GetMapping(value = "/test")
  public ResponseEntity<String> gqlTester () {

    return ResponseEntity.ok().body(aService.getData().getData().toString());
  }
}
