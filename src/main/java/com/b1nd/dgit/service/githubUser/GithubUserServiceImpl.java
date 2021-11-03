package com.b1nd.dgit.service.githubUser;

import com.b1nd.dgit.domain.entities.GithubUser;
import com.b1nd.dgit.domain.entities.User;
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

  public GithubUser update (GetContributionQuery.User githubUser) {
    return githubUserRepository.save(GithubUser.builder()
            .githubId(githubUser.login())
            .totalContributions(githubUser.contributionsCollection().contributionCalendar().totalContributions())
            .userImage(githubUser.avatarUrl().toString())
            .bio(githubUser.bio())
            .build()
    );
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
    return githubUserRepository.getById(githubId);
  }
}
