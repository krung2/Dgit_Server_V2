package com.b1nd.dgit.service.githubUser;

import com.b1nd.dgit.domain.repositories.user.GithubUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GithubUserServiceImpl implements GithubUserService {

  private final GithubUserRepository githubUserRepository;
}
