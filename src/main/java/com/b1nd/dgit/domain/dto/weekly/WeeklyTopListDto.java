package com.b1nd.dgit.domain.dto.weekly;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class WeeklyTopListDto {

  private Long idx;
  private String date;
  private int contribute;
  private WeeklyTopGithubUser weeklyTopGithubUser;
}
