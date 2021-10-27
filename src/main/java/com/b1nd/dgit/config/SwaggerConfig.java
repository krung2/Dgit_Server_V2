package com.b1nd.dgit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

  @Bean
  public Docket swaggerAPI () {
    return new Docket(DocumentationType.SWAGGER_2)
            .useDefaultResponseMessages(false)
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build()
            .apiInfo(swaggerInfo());
  }

  private ApiInfo swaggerInfo() {
    return new ApiInfoBuilder().title("Dgit Server API Documentation")
            .description("dgit server v2의 API문서 입니다")
            .license("DGIT").licenseUrl("https://github.com/krung2/Dgit_Server_V2")
            .version("1.0.0")
            .build();
  }
}
