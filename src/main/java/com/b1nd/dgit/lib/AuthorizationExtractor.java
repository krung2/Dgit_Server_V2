package com.b1nd.dgit.lib;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Component
public class AuthorizationExtractor {

  public static String AUTHORIZATION = "Authorization";

  public static String extract(HttpServletRequest request, String type) {
    Enumeration<String> headers = request.getHeaders(AUTHORIZATION);

    while (headers.hasMoreElements()) {
      String value = headers.nextElement();
      if (value.toLowerCase().startsWith(type.toLowerCase())) {
        return value.substring(type.length()).trim();
      }
    }

    return Strings.EMPTY;
  }
}
