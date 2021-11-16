package com.b1nd.dgit.service.github;

import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Response;
import com.b1nd.dgit.domain.model.http.errors.BadRequestErrorException;
import com.b1nd.dgit.lib.ApolloClientUtils;
import github.queries.GetContributionQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
@RequiredArgsConstructor
public class GithubServiceImpl implements GithubService {

  private final ApolloClient githubApolloClient;

  @Override
  public Response<GetContributionQuery.Data> getData(String userId) {
    Response<GetContributionQuery.Data> responseData = getResponseData(userId);

    if (responseData.getData().user() == null) {
      throw BadRequestErrorException.of("존재하지 않는 githubId 입니다");
    }

    return responseData;
  }

  private Response<GetContributionQuery.Data> getResponseData(@NotNull String userId) {
    return ApolloClientUtils.toCompletableFuture(githubApolloClient.query(
                    GetContributionQuery
                            .builder()
                            .login(userId)
                            .build())
            .toBuilder().build()
    ).join();
  }
}