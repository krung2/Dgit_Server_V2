package com.b1nd.dgit.config;

import com.apollographql.apollo.ApolloClient;
import lombok.RequiredArgsConstructor;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ApolloClientConfig {

  @Bean
  public ApolloClient githubApolloClient(final AppProperties appProperties) {
    return ApolloClient.builder()
            .serverUrl("https://api.github.com/graphql")
            .okHttpClient(getOkHttpClient(appProperties.getGithubToken()))
            .build();
  }

  private OkHttpClient getOkHttpClient (final String token) {
    return new OkHttpClient.Builder()
            .addInterceptor(getApiKeyInterceptor(token))
            .build();
  }

  private Interceptor getApiKeyInterceptor(final String token) {
    return chain -> chain.proceed(chain.request().newBuilder()
            .addHeader("authorization", "token " + token)
            .build()
    );
  }
}
