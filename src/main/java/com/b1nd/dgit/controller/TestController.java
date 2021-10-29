package com.b1nd.dgit.controller;

import github.queries.GetContributionQuery;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Nullable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController {

  private final AService aService;

  public TestController (AService aService)  {
    this.aService = aService;
  }

  @GetMapping(value = "/test")
  @ResponseBody()
  public ResponseEntity<String> gqlTester () {

    return ResponseEntity.ok().body(aService.getData().getData().toString());
  }
}
