package com.b1nd.dgit.service.auth;

import com.b1nd.dgit.service.token.TokenService;
import com.b1nd.dgit.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final UserService userServiceImpl;
  private final TokenService tokenServiceImpl;


}