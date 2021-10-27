package com.b1nd.dgit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class DgitApplication {

  public static void main(String[] args) {
    SpringApplication.run(DgitApplication.class, args);
  }
}
