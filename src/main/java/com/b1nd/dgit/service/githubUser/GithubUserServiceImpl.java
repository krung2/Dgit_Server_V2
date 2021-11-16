package com.b1nd.dgit.service.githubUser;

import com.b1nd.dgit.domain.entities.GithubUser;
import com.b1nd.dgit.domain.entities.User;
import com.b1nd.dgit.domain.model.http.errors.UnauthorizedException;
import com.b1nd.dgit.domain.repositories.user.GithubUserRepository;
import github.queries.GetContributionQuery;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GithubUserServiceImpl implements GithubUserService {

  private final GithubUserRepository githubUserRepository;

  @Transactional
  public GithubUser save(final User user, @NonNull GetContributionQuery.User githubUser) {
    return githubUserRepository.save(githubUserResponseToEntity(user, githubUser));
  }

  private GithubUser githubUserResponseToEntity(final User user, @NonNull GetContributionQuery.User githubUser) {
    return GithubUser.builder()
            .githubId(githubUser.login())
            .user(user)
            .totalContributions(githubUser.contributionsCollection().contributionCalendar().totalContributions())
            .userImage(githubUser.avatarUrl().toString())
            .bio(githubUser.bio())
            .build();
  }

  @Transactional
  public GithubUser update(final GithubUser userData, final GetContributionQuery.User githubUser) {
    return userData.update(
            githubUser.contributionsCollection().contributionCalendar().totalContributions(),
            githubUser.avatarUrl().toString(),
            githubUser.bio()
    );
  }

  public void remove(final GithubUser githubUser) {
    githubUserRepository.delete(githubUser);
  }

  @Transactional(readOnly = true)
  public List<GithubUser> getGithubUserListSort() {
    return githubUserRepository.findEntityGraph(Sort.by(Sort.Direction.DESC, "totalContributions"));
  }

  @Transactional(readOnly = true)
  public List<GithubUser> getGithubUserList() {
    return githubUserRepository.findEntityGraph();
  }

  @Transactional(readOnly = true)
  public GithubUser findById(final String githubId) {
    return githubUserRepository.findById(githubId)
            .orElseThrow(() -> UnauthorizedException.of("존재하지 않는 계정입니다"));
  }

  public boolean existUser(final String githubId) {
    return githubUserRepository.existsById(githubId);
  }
}
