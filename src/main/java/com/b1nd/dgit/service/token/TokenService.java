package com.b1nd.dgit.service.token;

import com.b1nd.dgit.domain.entities.User;
import com.b1nd.dgit.enums.jwt.JwtAuth;

public interface TokenService {

  String generateToken(String userId, JwtAuth jwtAuth);

  User validateToken(String token);

  String refreshToken(String refreshToken);

}
