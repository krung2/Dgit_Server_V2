package com.b1nd.dgit.controller;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import github.queries.GetContributionQuery;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController {

  @GetMapping(value = "/test")
  @ResponseBody()
  public ResponseEntity<Object> gqlTester () {

    Object a = new Object();

    ApolloClient apolloClient = ApolloClient.builder()
            .serverUrl("https://api.github.com/graphql")
//            .okHttpClient(OkHttpClient.Builder().build())
            .build();

    apolloClient.query(new GetContributionQuery("krung2"))
            .enqueue(new ApolloCall.Callback<>() {
              @Override
              public void onResponse(@NotNull Response<GetContributionQuery.Data> response) {
                GetContributionQuery.Data b = response.getData();

                System.out.println(b.toString());

//                return new ResponseEntity<GetContributionQuery.Data>(b, HttpStatus.OK);
              }

              @Override
              public void onFailure(@NotNull ApolloException e) {
                log.error(e.toString());
              }
            });

    return ResponseEntity.ok(a);
  }
}
