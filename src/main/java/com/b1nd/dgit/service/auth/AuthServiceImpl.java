package com.b1nd.dgit.service.auth;

import com.b1nd.dgit.config.AppProperties;
import com.b1nd.dgit.config.restTemplate.RestTemplateConfig;
import com.b1nd.dgit.domain.dto.auth.DodamLoginDto;
import com.b1nd.dgit.domain.dto.dodam.DauthServerDto;
import com.b1nd.dgit.domain.dto.dodam.DauthRequestDto;
import com.b1nd.dgit.domain.dto.dodam.DodamOpenApiDto;
import com.b1nd.dgit.domain.ro.auth.LoginRo;
import com.b1nd.dgit.service.token.TokenService;
import com.b1nd.dgit.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final UserService userServiceImpl;
  private final TokenService tokenServiceImpl;
  private final RestTemplateConfig restTemplateConfig;
  private final AppProperties appProperties;

private DodamOpenApiDto.DodamInfoData getCodeToDodamInfo (final String code) {
    DauthRequestDto requestDto = new DauthRequestDto(
            code,
            appProperties.getClientId(),
            appProperties.getClientSecret()
    );
    DauthServerDto dauthServerDto = restTemplateConfig.dodamAuthTemplate().postForObject("/token", new HttpEntity<>(requestDto, null), DauthServerDto.class);

    HttpHeaders headers = new HttpHeaders();
    headers.add("authorization", "Bearer " + dauthServerDto.getData().getAccess_token());
    return restTemplateConfig.dodamOpenTemplate().exchange(
            "/user",
            HttpMethod.GET,
            new HttpEntity<String>("parameters", headers),
            DodamOpenApiDto.class
    ).getBody().getData();
  }

  @Override
  public LoginRo dodamLogin(DodamLoginDto dodamLoginDto) {

    DodamOpenApiDto.DodamInfoData dodamInfoData = getCodeToDodamInfo(dodamLoginDto.getCode());
    return null;
  }
}