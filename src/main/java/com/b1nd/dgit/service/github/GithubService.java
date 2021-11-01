package com.b1nd.dgit.service.github;

import com.apollographql.apollo.api.Response;
import github.queries.GetContributionQuery;

public interface GithubService {

  Response<GetContributionQuery.Data> getData(String userId);
}
