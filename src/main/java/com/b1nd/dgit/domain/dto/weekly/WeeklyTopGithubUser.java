package com.b1nd.dgit.domain.dto.weekly;

import com.b1nd.dgit.domain.entities.GithubUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class WeeklyTopGithubUser {

  private String githubId;
  private int totalContributions;
  private String userImage;
  private String bio;
  private WeeklyTopUser weeklyTopUser;

  public static WeeklyTopGithubUser of (GithubUser githubUser) {
    return WeeklyTopGithubUser.builder()
            .githubId(githubUser.getGithubId())
            .totalContributions(githubUser.getTotalContributions())
            .userImage(githubUser.getUserImage())
            .bio(githubUser.getBio())
            .weeklyTopUser(WeeklyTopUser.of(githubUser.getUser()))
            .build();
  }
}
