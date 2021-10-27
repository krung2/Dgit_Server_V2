package com.b1nd.dgit.controller;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Response;
import com.b1nd.dgit.lib.ApolloClientUtils;
import github.queries.GetContributionQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AService {

  private final ApolloClient githubApolloClient;

  public Response<GetContributionQuery.Data> getData () {

    final ApolloCall<GetContributionQuery.Data> userData = githubApolloClient.query(GetContributionQuery.builder()
                    .login("krung2")
                    .build())
            .toBuilder().build();

    return ApolloClientUtils.toCompletableFuture(userData).join();
  }
}
