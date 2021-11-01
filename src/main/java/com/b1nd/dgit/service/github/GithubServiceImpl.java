package com.b1nd.dgit.service.github;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Response;
import com.b1nd.dgit.lib.ApolloClientUtils;
import github.queries.GetContributionQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GithubServiceImpl implements GithubService {

  private final ApolloClient githubApolloClient;

  @Override
  public Response<GetContributionQuery.Data> getData(String userId) {

    final ApolloCall<GetContributionQuery.Data> userData = githubApolloClient.query(
                    GetContributionQuery
                            .builder()
                            .login(userId)
                            .build())
            .toBuilder().build();

    return ApolloClientUtils.toCompletableFuture(userData).join();
  }
}