package com.b1nd.dgit.service.user;

import com.b1nd.dgit.domain.dto.dodam.DodamOpenApiDto;
import com.b1nd.dgit.domain.dto.user.ModifyGithubDto;
import com.b1nd.dgit.domain.entities.User;
import com.b1nd.dgit.domain.model.http.errors.BadRequestErrorException;
import com.b1nd.dgit.domain.repositories.user.UserRepository;
import com.b1nd.dgit.service.github.GithubService;
import com.b1nd.dgit.service.githubUser.GithubUserService;
import github.queries.GetContributionQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final GithubService githubServiceImpl;
  private final GithubUserService githubUserServiceImpl;

  public User save (DodamOpenApiDto dodamOpenApiDto) {
    DodamOpenApiDto.DodamInfoData dodamInfoData = dodamOpenApiDto.getData();
    return userRepository.save(
            User
                    .builder()
                    .id(dodamInfoData.getUniqueId())
                    .name(dodamInfoData.getName())
                    .build()
    );
  }

  @Override
  public User findById(String id) {
    return this.userRepository.findById(id)
            .orElseThrow(() -> BadRequestErrorException.of("존재하지 않는 유저입니다"));
  }

  @Override
  public void modifyGithubId(String userId, ModifyGithubDto modifyGithubDto) {
    User user = userRepository.save(findById(userId).update(modifyGithubDto.getGithubId()));
    GetContributionQuery.Data githubData = githubServiceImpl.getData(user.getGithub()).getData();
    githubUserServiceImpl.save(user, githubData.user());
  }
}
