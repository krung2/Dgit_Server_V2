package com.b1nd.dgit.domain.dto.weekly;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WeeklyTopGithubUser {

  private String githubId;
  private int totalContributions;
  private String userImage;
  private String bio;
  private WeeklyTopUser weeklyTopUser;
}
