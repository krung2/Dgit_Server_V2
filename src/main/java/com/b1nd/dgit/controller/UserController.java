package com.b1nd.dgit.controller;

import com.b1nd.dgit.annotation.UserLoginToken;
import com.b1nd.dgit.domain.dto.user.ModifyGithubDto;
import com.b1nd.dgit.domain.entities.User;
import com.b1nd.dgit.domain.model.http.Response;
import com.b1nd.dgit.service.user.UserService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = {"User"})
@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {

  private final UserService userServiceImpl;

  @UserLoginToken
  @PutMapping(value = "/github")
  public ResponseEntity<Response> modifyGithubId(
          final @RequestAttribute User user,
          final @Valid @RequestBody ModifyGithubDto modifyGithubDto
  ) {
    userServiceImpl.modifyGithubId(user.getId(), modifyGithubDto);
    return Response.ok("깃허브 수정 성공");
  }
}
