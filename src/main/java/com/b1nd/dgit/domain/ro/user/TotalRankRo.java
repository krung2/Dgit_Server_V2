package com.b1nd.dgit.domain.ro.user;

import com.b1nd.dgit.domain.entities.GithubUser;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TotalRankRo {

  private List<GithubUser> users;
  private int totalTop;

  @Builder
  public TotalRankRo(List<GithubUser> users, int totalTop) {
    this.users = users;
    this.totalTop = totalTop;
  }
}
