package com.b1nd.dgit.service.token;

import com.b1nd.dgit.config.AppProperties;
import com.b1nd.dgit.domain.entities.User;
import com.b1nd.dgit.domain.model.http.errors.BadRequestErrorException;
import com.b1nd.dgit.domain.model.http.errors.CustomError;
import com.b1nd.dgit.domain.model.http.errors.ErrorCodes;
import com.b1nd.dgit.domain.model.http.errors.GoneRequestErrorException;
import com.b1nd.dgit.enums.jwt.JwtAuth;
import com.b1nd.dgit.service.user.UserService;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

  private static final long JWT_ACCESS_EXPIRE = 1000 * 60 * 60;
  private static final long JWT_REFRESH_EXPIRE = 1000 * 60 * 60 * 24 * 7;

  private static final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

  private final AppProperties appProperties;
  private final UserService userServiceImpl;

  @Override
  public String generateToken(String userId, JwtAuth jwtAuth) {

    Date expiredAt = new Date();
    String secretKey;

    if (jwtAuth == JwtAuth.ACCESS) {
      expiredAt = new Date(expiredAt.getTime() + JWT_ACCESS_EXPIRE);
      secretKey = appProperties.getSecret();
    } else {
      expiredAt = new Date(expiredAt.getTime() + JWT_REFRESH_EXPIRE);
      secretKey = appProperties.getRefreshSecret();
    }

    Map<String ,Object> claims = new HashMap<>();
    claims.put("userId", userId);

    return Jwts.builder()
            .setClaims(claims)
            .setSubject(jwtAuth.toString())
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(expiredAt)
            .signWith(signatureAlgorithm, secretKey)
            .compact();
  }

  private Claims parseToken (String token, JwtAuth jwtAuth) {
    try {
      return Jwts.parser()
              .setSigningKey(
                      jwtAuth == JwtAuth.ACCESS
                              ? appProperties.getSecret()
                              : appProperties.getRefreshSecret()
              )
              .parseClaimsJws(token)
              .getBody();
    } catch (ExpiredJwtException e) {
      throw GoneRequestErrorException.of("토큰이 만료되었습니다");
    } catch (SignatureException | MalformedJwtException e) {
      throw CustomError.of(ErrorCodes.TOKEN_FORGED_ERROR);
    } catch (IllegalArgumentException e) {
      throw BadRequestErrorException.of("토큰이 없습니다");
    } catch (Exception e) {
      log.error("Token 해석 중 에러 발생! : {}", e.toString());
      throw CustomError.of(ErrorCodes.INTERNAL_SERVER_ERROR);
    }
  }

  @Override
  public User validateToken(String token) {

    return userServiceImpl.findById(
            parseToken(token, JwtAuth.ACCESS).get("userId").toString()
    );
  }

  @Override
  public String RefreshToken(String refreshToken) {

    if (refreshToken == null || refreshToken.trim().isEmpty()) {
      throw BadRequestErrorException.of("토큰이 전송되지 않았습니다");
    }

    Claims claims = this.parseToken(refreshToken, JwtAuth.REFRESH);
    User user = userServiceImpl.findById(claims.get("userId").toString());

    return generateToken(user.getId(), JwtAuth.ACCESS);
  }
}
