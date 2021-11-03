package com.b1nd.dgit.controller;

import com.b1nd.dgit.domain.model.http.ResponseData;
import com.b1nd.dgit.domain.ro.week.WeeklyRankRo;
import com.b1nd.dgit.service.week.WeekService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = {"Week"})
@RestController
@RequestMapping(value = "/week")
@RequiredArgsConstructor
public class WeekController {

  private final WeekService weekServiceImpl;

  @GetMapping(value = "")
  public ResponseEntity<ResponseData<List<WeeklyRankRo>>> getWeeklyData () {
    return ResponseData.ok("주별 랭킹 조회 성공", weekServiceImpl.getWeeklyRanking());
  }
}
