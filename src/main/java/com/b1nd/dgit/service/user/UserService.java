package com.b1nd.dgit.service.user;

import com.b1nd.dgit.domain.entities.User;

public interface UserService {

  User findById(String id);
}
