package com.b1nd.dgit.service.auth;

import com.b1nd.dgit.config.AppProperties;
import com.b1nd.dgit.config.restTemplate.RestTemplateConfig;
import com.b1nd.dgit.domain.dto.auth.DodamLoginDto;
import com.b1nd.dgit.domain.dto.dodam.DauthRequestDto;
import com.b1nd.dgit.domain.dto.dodam.DauthServerDto;
import com.b1nd.dgit.domain.dto.dodam.DodamOpenApiDto;
import com.b1nd.dgit.domain.entities.User;
import com.b1nd.dgit.domain.ro.auth.LoginRo;
import com.b1nd.dgit.enums.jwt.JwtAuth;
import com.b1nd.dgit.service.token.TokenService;
import com.b1nd.dgit.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final UserService userServiceImpl;
  private final TokenService tokenServiceImpl;
  private final RestTemplateConfig restTemplateConfig;
  private final AppProperties appProperties;

  private DodamOpenApiDto getCodeToDodamInfo(final String code) {
    log.info("--------dodam Server request--------");
    HttpHeaders headers = new HttpHeaders();
    headers.add("authorization", "Bearer " + getDAuthToken(code).getAccess_token());
    return restTemplateConfig.dodamOpenTemplate().exchange(
            "/user",
            HttpMethod.GET,
            new HttpEntity<>(headers),
            DodamOpenApiDto.class
    ).getBody();
  }

  private DauthServerDto getDAuthToken(@NotNull String code) {
    log.info("--------dauth Server request--------");
    return restTemplateConfig.dodamAuthTemplate()
            .postForObject("/token", new HttpEntity<>(
                    DauthRequestDto.builder()
                            .code(code)
                            .client_id(appProperties.getClientId())
                            .client_secret(appProperties.getClientSecret())
                            .build(),
                    null
            ), DauthServerDto.class);
  }

  @Override
  public LoginRo dodamLogin(DodamLoginDto dodamLoginDto) {
    User user = userServiceImpl.save(getCodeToDodamInfo(dodamLoginDto.getCode()));
    return LoginRo.builder()
            .user(user)
            .token(tokenServiceImpl.generateToken(user.getId(), JwtAuth.ACCESS))
            .refreshToken(tokenServiceImpl.generateToken(user.getId(), JwtAuth.REFRESH))
            .build();
  }
}