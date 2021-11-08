package com.b1nd.dgit.service.user;

import com.b1nd.dgit.domain.dto.dodam.DodamOpenApiDto;
import com.b1nd.dgit.domain.entities.User;
import com.b1nd.dgit.domain.repositories.user.UserRepository;
import com.b1nd.dgit.service.github.GithubService;
import com.b1nd.dgit.service.githubUser.GithubUserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

  @Mock
  private UserRepository userRepository;

  @Mock
  private GithubService githubService;

  @Mock
  private GithubUserService githubUserService;

  @InjectMocks
  private UserServiceImpl userService;

  @Test
  public void saveUserTest_success() {
    DodamOpenApiDto dodamOpenApiDto = new DodamOpenApiDto(
            200,
            "불러오기 성공",
            DodamOpenApiDto.DodamInfoData.builder()
                    .uniqueId("123")
                    .grade(2)
                    .room(2)
                    .number(10)
                    .name("신")
                    .email("jungbin4337@gmail.com")
                    .profileImage("")
                    .accessLevel(1)
                    .build()
    );
    ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);

    User user = userService.save(dodamOpenApiDto);

    verify(userRepository, times(1))
            .save(captor.capture());
    User savedUser = captor.getValue();
    assertEquals("123", savedUser.getId());
    assertEquals("신", savedUser.getName());
  }
}