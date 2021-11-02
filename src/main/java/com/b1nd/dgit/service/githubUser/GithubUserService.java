package com.b1nd.dgit.service.githubUser;

import com.b1nd.dgit.domain.entities.GithubUser;
import com.b1nd.dgit.domain.entities.User;
import github.queries.GetContributionQuery;

import java.util.List;

public interface GithubUserService {

  GithubUser save (User user, GetContributionQuery.User githubUser);
  GithubUser update (GetContributionQuery.User githubUser);
  void remove (GithubUser githubUser);
  List<GithubUser> githubUserList ();
}
