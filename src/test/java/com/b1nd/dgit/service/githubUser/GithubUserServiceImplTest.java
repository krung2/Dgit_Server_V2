package com.b1nd.dgit.service.githubUser;

import com.b1nd.dgit.domain.repositories.user.GithubUserRepository;
import com.b1nd.dgit.domain.repositories.user.UserRepository;
import com.b1nd.dgit.service.user.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class GithubUserServiceImplTest  {

  @Mock
  private GithubUserRepository githubUserRepository;

  @InjectMocks
  private GithubUserServiceImpl githubUserService;


  @Test
  void existUserTest() {
  }

}