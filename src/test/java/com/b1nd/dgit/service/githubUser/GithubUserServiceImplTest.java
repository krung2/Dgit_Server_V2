package com.b1nd.dgit.service.githubUser;

import com.b1nd.dgit.domain.entities.GithubUser;
import com.b1nd.dgit.domain.repositories.user.GithubUserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

class GithubUserServiceImplTest {

  @Mock
  private GithubUserRepository githubUserRepository;

  @InjectMocks
  private GithubUserServiceImpl githubUserService;

  @Test
  void existUserTest() {
    given(githubUserRepository.findById(anyString()))
            .willReturn(Optional.of(new GithubUser()));

    boolean test = githubUserService.existUser("123");

    assertTrue(test);
  }

}