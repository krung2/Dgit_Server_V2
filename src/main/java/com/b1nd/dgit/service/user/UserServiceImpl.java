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
import org.springframework.lang.Nullable;
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
  public User save(DodamOpenApiDto dodamOpenApiDto) {
    return userRepository.save(
            DodamOpenApiDto.DodamInfoData.toEntity(dodamOpenApiDto.getData())
    );
  }

  @Override
  @Transactional(readOnly = true)
  public User findById(String id) {
    return userRepository.findById(id)
            .orElseThrow(() -> BadRequestErrorException.of("존재하지 않는 유저입니다"));
  }

  @Override
  // 로직 안에서 Error를 throw하므로, @Transaction을 사용하는 것은 고려해보아야 할 것 같습니다
  public void modifyGithubId(User user, ModifyGithubDto modifyGithubDto) {
    existUser(modifyGithubDto.getGithubId());
    GetContributionQuery.Data githubData = githubServiceImpl.getData(modifyGithubDto.getGithubId()).getData();
    githubUserServiceImpl.remove(getGithubUserToUser(user.getGithubUser()));
    githubUserServiceImpl.save(user, githubData.user());
  }

  private void existUser(String githubId) {
    if (githubUserServiceImpl.existUser(githubId))
      throw UnauthorizedException.of("이미 존재하는 계정입니다");
  }

  private GithubUser getGithubUserToUser(@Nullable GithubUser githubUser) {
    return githubUser == null ? new GithubUser() : githubUser;
  }
}
