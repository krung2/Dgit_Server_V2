package com.b1nd.dgit.controller;

import com.b1nd.dgit.domain.dto.token.RefreshTokenDto;
import com.b1nd.dgit.domain.model.http.ResponseData;
import com.b1nd.dgit.service.token.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController(value = "/token")
@RequiredArgsConstructor
public class TokenController {

  private final TokenService tokenServiceImpl;

  @PostMapping(value = "/refresh")
  public ResponseEntity<ResponseData<String>> refreshToken (final @RequestBody @Valid RefreshTokenDto refreshTokenDto) {
    return ResponseData.ok("토큰 재발급 성공", tokenServiceImpl.refreshToken(refreshTokenDto.getRefreshToken()));
  }
}
