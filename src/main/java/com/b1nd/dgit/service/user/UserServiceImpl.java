package com.b1nd.dgit.service.user;

import com.b1nd.dgit.domain.dto.dodam.DodamOpenApiDto;
import com.b1nd.dgit.domain.dto.user.ModifyGithubDto;
import com.b1nd.dgit.domain.entities.GithubUser;
import com.b1nd.dgit.domain.entities.User;
import com.b1nd.dgit.domain.model.http.errors.BadRequestErrorException;
import com.b1nd.dgit.domain.model.http.errors.UnauthorizedException;
import com.b1nd.dgit.domain.repositories.user.UserRepository;
import com.b1nd.dgit.service.github.GithubService;
import com.b1nd.dgit.service.githubUser.GithubUserService;
import github.queries.GetContributionQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final GithubService githubServiceImpl;
  private final GithubUserService githubUserServiceImpl;

  @Override
  @Transactional
  public User save (DodamOpenApiDto dodamOpenApiDto) {
    DodamOpenApiDto.DodamInfoData dodamInfoData = dodamOpenApiDto.getData();
    return userRepository.save(DodamOpenApiDto.DodamInfoData.toEntity(dodamInfoData));
  }

  @Override
  public User findById(String id) {
    return userRepository.findById(id)
            .orElseThrow(() -> BadRequestErrorException.of("존재하지 않는 유저입니다"));
  }

  @Override
  @Transactional
  public void modifyGithubId(User user, ModifyGithubDto modifyGithubDto) {
    if (githubUserServiceImpl.existUser(modifyGithubDto.getGithubId())) throw UnauthorizedException.of("이미 존재하는 계정입니다");
    GetContributionQuery.Data githubData = githubServiceImpl.getData(modifyGithubDto.getGithubId()).getData();
    githubUserServiceImpl.remove(user.getGithubUser() == null ? new GithubUser() : user.getGithubUser());
    githubUserServiceImpl.save(user, githubData.user());
  }
}
