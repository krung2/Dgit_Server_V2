package com.b1nd.dgit.controller;

import com.b1nd.dgit.annotation.UserLoginToken;
import com.b1nd.dgit.domain.dto.user.ModifyGithubDto;
import com.b1nd.dgit.domain.entities.GithubUser;
import com.b1nd.dgit.domain.entities.User;
import com.b1nd.dgit.domain.model.http.Response;
import com.b1nd.dgit.domain.model.http.ResponseData;
import com.b1nd.dgit.service.user.UserService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    userServiceImpl.modifyGithubId(user, modifyGithubDto);
    return Response.ok("깃허브 수정 성공");
  }

  @UserLoginToken
  @GetMapping("/")
  public ResponseEntity<ResponseData<User>> getUser (@RequestAttribute User user) {
    return ResponseData.ok("자신의 정보 가져오기 성공", userServiceImpl.findById(user.getId()));
  }

  @GetMapping("/all")
  public ResponseEntity<ResponseData<List<GithubUser>>> githubUserList () {
    return ResponseData.ok("깃허브 유저 가져오기 성공", userServiceImpl.githubUserListSort());
  }
}
