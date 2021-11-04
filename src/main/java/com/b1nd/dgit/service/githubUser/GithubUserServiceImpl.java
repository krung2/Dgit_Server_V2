package com.b1nd.dgit.service.githubUser;

import com.b1nd.dgit.domain.entities.GithubUser;
import com.b1nd.dgit.domain.entities.User;
import com.b1nd.dgit.domain.model.http.errors.UnauthorizedException;
import com.b1nd.dgit.domain.repositories.user.GithubUserRepository;
import github.queries.GetContributionQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GithubUserServiceImpl implements GithubUserService {

  private final GithubUserRepository githubUserRepository;

  public GithubUser save (User user, GetContributionQuery.User githubUser) {
    return githubUserRepository.save(GithubUser.builder()
            .githubId(githubUser.login())
            .user(user)
            .totalContributions(githubUser.contributionsCollection().contributionCalendar().totalContributions())
            .userImage(githubUser.avatarUrl().toString())
            .bio(githubUser.bio())
            .build()
    );
  }

  public GithubUser update (GithubUser userData, GetContributionQuery.User githubUser) {
    return githubUserRepository.save(userData.update(
            githubUser.contributionsCollection().contributionCalendar().totalContributions(),
            githubUser.avatarUrl().toString(),
            githubUser.bio()
            ));
  }

  public void remove (GithubUser githubUser) {
    githubUserRepository.delete(githubUser);
  }

  public List<GithubUser> githubUserListSort () {
    return githubUserRepository.findEntityGraph(Sort.by(Sort.Direction.DESC, "totalContributions"));
  }

  public List<GithubUser> githubUserList () {
    return githubUserRepository.findEntityGraph();
  }

  public GithubUser findById (String githubId) {
    return githubUserRepository.findById(githubId).orElseThrow(() -> UnauthorizedException.of("존재하지 않는 계정입니다"));
  }

  public boolean existUser (String githubId) {
    return githubUserRepository.findById(githubId).isPresent();
  }
}
