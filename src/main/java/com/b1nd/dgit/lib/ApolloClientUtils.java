package com.b1nd.dgit.lib;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.sun.istack.NotNull;

import java.util.concurrent.CompletableFuture;

public class ApolloClientUtils {

  public static <T> CompletableFuture<Response<T>> toCompletableFuture(@NotNull ApolloCall<T> apolloCall) {
    CompletableFuture<Response<T>> completableFuture = new CompletableFuture<>();

    completableFuture.whenComplete((tResponse, throwable) -> {
      if (completableFuture.isCancelled()) {
        completableFuture.cancel(true);
      }
    });

    apolloCall.enqueue(new ApolloCall.Callback<T>() {
      @Override
      public void onResponse(@NotNull Response<T> response) {
        completableFuture.complete(response);
      }

      @Override
      public void onFailure(@NotNull ApolloException e) {
        completableFuture.completeExceptionally(e);
      }
    });

    return completableFuture;
  }
}
