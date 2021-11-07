package com.b1nd.dgit.controller;

import com.b1nd.dgit.domain.model.http.ResponseData;
import com.b1nd.dgit.domain.ro.user.TotalRankRo;
import com.b1nd.dgit.service.total.TotalTopService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"Total"})
@RestController
@RequestMapping(value = "/total")
@RequiredArgsConstructor
public class TotalController {

  private final TotalTopService totalTopServiceImpl;

  @GetMapping
  public ResponseEntity<ResponseData<TotalRankRo>> getTotalRank() {
    return ResponseData.ok(
            "순위 조회 성공",
            totalTopServiceImpl.getTotalRank()
    );
  }
}
