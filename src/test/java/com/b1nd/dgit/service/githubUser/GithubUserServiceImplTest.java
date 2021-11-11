package com.b1nd.dgit.service.githubUser;

import com.b1nd.dgit.domain.entities.GithubUser;
import com.b1nd.dgit.domain.repositories.user.GithubUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class GithubUserServiceImplTest {

  @Mock
  private GithubUserRepository githubUserRepository;

  @InjectMocks
  private GithubUserServiceImpl githubUserService;

  @Test
  void existUserTest_Success() {
    given(githubUserRepository.existsById(anyString()))
            .willReturn(true);

    boolean test = githubUserService.existUser("123");

    assertTrue(test);
  }

  @Test
  void existUserTest_Failed() {
    given(githubUserRepository.existsById(anyString()))
            .willReturn(false);

    boolean test = githubUserService.existUser("123");

    assertFalse(test);
  }
}