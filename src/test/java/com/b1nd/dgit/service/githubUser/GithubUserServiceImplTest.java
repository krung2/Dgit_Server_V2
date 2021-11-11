package com.b1nd.dgit.service.githubUser;

import com.b1nd.dgit.domain.entities.GithubUser;
import com.b1nd.dgit.domain.entities.User;
import com.b1nd.dgit.domain.repositories.user.GithubUserRepository;
import github.queries.GetContributionQuery;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
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

  final User mockUser = User.builder()
          .id("123")
          .name("신중빈")
          .build();

  final GithubUser mockGithubUser = GithubUser.builder()
          .githubId("krung2")
          .user(mockUser)
          .totalContributions(123)
          .userImage("")
          .bio("안농!")
          .build();

  @Test
  void githubUserSaveTest_Success() { // GQL mock을 생성하는 법을 알아보아야 할 것 같음
    // given

    // when

    // then

  }

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