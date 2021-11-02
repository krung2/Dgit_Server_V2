package com.b1nd.dgit.service.githubUser;

import com.b1nd.dgit.domain.entities.GithubUser;
import com.b1nd.dgit.domain.entities.User;
import com.b1nd.dgit.domain.model.http.errors.BadRequestErrorException;
import com.b1nd.dgit.domain.repositories.user.GithubUserRepository;
import github.queries.GetContributionQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

  public void remove (GithubUser githubUser) {
    githubUserRepository.delete(githubUser);
  }
}
