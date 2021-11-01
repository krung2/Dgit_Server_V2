package com.b1nd.dgit.service.auth;

import com.b1nd.dgit.domain.dto.auth.DodamLoginDto;
import com.b1nd.dgit.domain.ro.auth.LoginRo;

public interface AuthService {

  LoginRo dodamLogin (DodamLoginDto dodamLoginDto);
}