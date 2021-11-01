package com.b1nd.dgit.interceptor;

import com.b1nd.dgit.annotation.UserLoginToken;
import com.b1nd.dgit.domain.entities.User;
import com.b1nd.dgit.domain.model.http.errors.UnauthorizedException;
import com.b1nd.dgit.lib.AuthorizationExtractor;
import com.b1nd.dgit.service.token.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {

  private final TokenService tokenServiceImpl;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
    log.info("request url : {}", request.getRequestURI());

    HandlerMethod handlerMethod = (HandlerMethod)handler;

    if (!handlerMethod.getMethod().isAnnotationPresent(UserLoginToken.class)) {
      return true;
    }

    String token = AuthorizationExtractor.extract(request, "Bearer");
    if (Objects.equals(token, "")) {
      throw UnauthorizedException.of("토큰이 입력되지 않았습니다");
    }

    User user = tokenServiceImpl.validateToken(token);
    request.setAttribute("user", user);

    return true;
  }
}
