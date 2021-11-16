package com.b1nd.dgit.config.restTemplate;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

import static com.b1nd.dgit.enums.endpoint.DodamEndPoint.DODAM_AUTH;
import static com.b1nd.dgit.enums.endpoint.DodamEndPoint.DODAM_OPENAPI;

@Configuration
@RequiredArgsConstructor
public class RestTemplateConfig {

  private final RestTemplateBuilder restTemplateBuilder;

  private RestTemplate restTemplate(final String endpoint) {
    return restTemplateBuilder.rootUri(endpoint)
            .additionalInterceptors(new RestTemplateClientHttpRequestInterceptor())
            .errorHandler(new RestTemplateErrorHandler())
            .setConnectTimeout(Duration.ofMinutes(3))
            .build();
  }

  @Bean
  public RestTemplate dodamAuthTemplate() {
    return this.restTemplate(DODAM_AUTH.toString());
  }

  @Bean
  public RestTemplate dodamOpenTemplate() {
    return this.restTemplate(DODAM_OPENAPI.toString());
  }
}
