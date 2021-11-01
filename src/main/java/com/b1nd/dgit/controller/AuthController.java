package com.b1nd.dgit.controller;

import com.b1nd.dgit.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authServiceImpl;
}
