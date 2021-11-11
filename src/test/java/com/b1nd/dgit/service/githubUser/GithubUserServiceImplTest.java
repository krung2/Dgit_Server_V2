package com.b1nd.dgit.service.githubUser;

import com.b1nd.dgit.domain.entities.GithubUser;
import com.b1nd.dgit.domain.entities.User;
import com.b1nd.dgit.domain.repositories.user.GithubUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
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
  void getGithubUserList_Success() {
    // given
    given(githubUserRepository.findEntityGraph())
            .willReturn(List.of(mockGithubUser, mockGithubUser));

    // when
    List<GithubUser> githubUserList = githubUserService.getGithubUserList();

    // then
    assertEquals("krung2", githubUserList.get(0).getGithubId());
    assertEquals(mockUser, githubUserList.get(0).getUser());
    assertEquals(mockUser, githubUserList.get(1).getUser());
    assertEquals(123, githubUserList.get(1).getTotalContributions());
    assertEquals("", githubUserList.get(0).getUserImage());
    assertEquals("안농!", githubUserList.get(1).getBio());
    assertThrows(IndexOutOfBoundsException.class, () -> githubUserList.get(3));
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