package com.b1nd.dgit.controller;

import com.b1nd.dgit.domain.dto.auth.DodamLoginDto;
import com.b1nd.dgit.domain.model.http.ResponseData;
import com.b1nd.dgit.domain.ro.auth.LoginRo;
import com.b1nd.dgit.service.auth.AuthService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(tags = {"Auth"})
@RestController
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authServiceImpl;

  @PostMapping(value = "/login")
  public ResponseEntity<ResponseData<LoginRo>> login (final @Valid @RequestBody DodamLoginDto dodamLoginDto) {
    return ResponseData.ok(
            "로그인 성공",
            this.authServiceImpl.dodamLogin(dodamLoginDto)
    );
  }
}
