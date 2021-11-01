package com.b1nd.dgit.service.auth;

import com.b1nd.dgit.config.AppProperties;
import com.b1nd.dgit.config.restTemplate.RestTemplateConfig;
import com.b1nd.dgit.domain.dto.auth.DodamLoginDto;
import com.b1nd.dgit.domain.dto.dodam.DAuthServerDto;
import com.b1nd.dgit.domain.ro.auth.LoginRo;
import com.b1nd.dgit.service.token.TokenService;
import com.b1nd.dgit.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final UserService userServiceImpl;
  private final TokenService tokenServiceImpl;
  private final RestTemplateConfig restTemplateConfig;

  private DAuthServerDto getTokenInDodam (final String code) {
  }

  @Override
  public LoginRo dodamLogin(DodamLoginDto dodamLoginDto) {



  }
}