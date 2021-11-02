package com.b1nd.dgit.service.week;

import com.b1nd.dgit.domain.entities.GithubUser;
import com.b1nd.dgit.domain.entities.WeekContribute;
import github.queries.GetContributionQuery;

import java.util.List;

public interface WeekService {

  WeekContribute save(GithubUser githubUser, GetContributionQuery.ContributionDay contributionData);
  void deleteAllData ();
  List<WeekContribute> getAllData ();
}
