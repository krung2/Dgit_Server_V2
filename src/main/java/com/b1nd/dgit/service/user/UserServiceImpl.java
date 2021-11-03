package com.b1nd.dgit.service.user;

import com.b1nd.dgit.domain.dto.dodam.DodamOpenApiDto;
import com.b1nd.dgit.domain.dto.user.ModifyGithubDto;
import com.b1nd.dgit.domain.entities.GithubUser;
import com.b1nd.dgit.domain.entities.User;
import com.b1nd.dgit.domain.model.http.errors.BadRequestErrorException;
import com.b1nd.dgit.domain.repositories.user.UserRepository;
import com.b1nd.dgit.service.github.GithubService;
import com.b1nd.dgit.service.githubUser.GithubUserService;
import github.queries.GetContributionQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
    return userRepository.findById(id)
            .orElseThrow(() -> BadRequestErrorException.of("존재하지 않는 유저입니다"));
  }

  @Override
  public void modifyGithubId(User user, ModifyGithubDto modifyGithubDto) {
    githubUserServiceImpl.remove(user.getGithubUser() == null ? new GithubUser() : user.getGithubUser());
    GetContributionQuery.Data githubData = githubServiceImpl.getData(modifyGithubDto.getGithubId()).getData();
    githubUserServiceImpl.save(user, githubData.user());
  }

}
