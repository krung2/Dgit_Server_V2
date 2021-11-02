package com.b1nd.dgit.controller;

import com.b1nd.dgit.domain.model.http.Response;
import com.b1nd.dgit.service.schedule.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class SchedulerController {

  private final ScheduleService scheduleService;

  @GetMapping(value = "/data")
  public ResponseEntity<Response> test () {
    scheduleService.weekContributeCheck();
    return Response.ok("데이터 가져오기 성공");
  }
}
