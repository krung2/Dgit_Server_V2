package com.b1nd.dgit.service.user;

import com.b1nd.dgit.domain.dto.dodam.DodamOpenApiDto;
import com.b1nd.dgit.domain.dto.user.ModifyGithubDto;
import com.b1nd.dgit.domain.entities.User;

public interface UserService {

  User save(DodamOpenApiDto dodamOpenApiDto);
  User findById(String id);
  void modifyGithubId(ModifyGithubDto modifyGithubDto);
}
