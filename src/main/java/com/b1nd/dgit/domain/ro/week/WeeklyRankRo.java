package com.b1nd.dgit.domain.ro.week;

import com.b1nd.dgit.domain.entities.GithubUser;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeeklyRankRo {

  private GithubUser githubUser;
  private int weeklyCommit;

  @Builder
  public WeeklyRankRo(GithubUser githubUser, int weeklyCommit) {
    this.githubUser = githubUser;
    this.weeklyCommit = weeklyCommit;
  }

  public static WeeklyRankRo of(GithubUser githubUser, int weeklyCommit) {
    return WeeklyRankRo.builder()
            .githubUser(githubUser)
            .weeklyCommit(weeklyCommit)
            .build();
  }
}
