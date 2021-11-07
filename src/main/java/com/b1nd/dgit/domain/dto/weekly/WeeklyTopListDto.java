package com.b1nd.dgit.domain.dto.weekly;

import com.b1nd.dgit.domain.entities.WeeklyTop;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class WeeklyTopListDto {

  private Long idx;
  private String date;
  private int contribute;
  private WeeklyTopGithubUser weeklyTopGithubUser;

  public static WeeklyTopListDto of(WeeklyTop weeklyTop) {
    return WeeklyTopListDto.builder()
            .idx(weeklyTop.getIdx())
            .date(weeklyTop.getDate())
            .contribute(weeklyTop.getContribute())
            .weeklyTopGithubUser(WeeklyTopGithubUser.of(weeklyTop.getGithubUser()))
            .build();
  }
}
