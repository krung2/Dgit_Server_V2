package com.b1nd.dgit.interceptor;

import com.b1nd.dgit.annotation.UserLoginToken;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class AuthInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
    log.info("request url : {}", request.getRequestURI());

    HandlerMethod handlerMethod = (HandlerMethod)handler;




  }

  private boolean checkAnnotation(Object handler, Class clazz) {

    HandlerMethod handlerMethod = (HandlerMethod) handler;
    return null != handlerMethod.getMethodAnnotation(clazz) || null
            != handlerMethod.getBeanType().getAnnotation(clazz);
  }
}
