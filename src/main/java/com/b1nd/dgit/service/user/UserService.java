package com.b1nd.dgit.service.user;

import com.b1nd.dgit.domain.dto.dodam.DodamOpenApiDto;
import com.b1nd.dgit.domain.dto.user.ModifyGithubDto;
import com.b1nd.dgit.domain.entities.GithubUser;
import com.b1nd.dgit.domain.entities.User;

import java.util.List;

public interface UserService {

  User save(DodamOpenApiDto dodamOpenApiDto);
  User findById(String id);
  void modifyGithubId(User user, ModifyGithubDto modifyGithubDto);
  List<GithubUser> githubUserList ();
}
