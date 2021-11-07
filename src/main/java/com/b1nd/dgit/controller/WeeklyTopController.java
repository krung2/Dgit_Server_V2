package com.b1nd.dgit.controller;

import com.b1nd.dgit.domain.dto.weekly.WeeklyTopListDto;
import com.b1nd.dgit.domain.model.http.ResponseData;
import com.b1nd.dgit.service.weekly.WeeklyTopService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = {"Weekly-Top"})
@RestController
@RequestMapping(value = "/weekly")
@RequiredArgsConstructor
public class WeeklyTopController {

  private final WeeklyTopService weeklyTopServiceImpl;

  @GetMapping(value = "/top")
  public ResponseEntity<ResponseData<List<WeeklyTopListDto>>> getWeeklyData() {
    return ResponseData.ok("주별 기록 조회 성공", weeklyTopServiceImpl.findAllData());
  }
}
