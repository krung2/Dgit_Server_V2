package com.b1nd.dgit.service.user;

import com.b1nd.dgit.domain.dto.dodam.DodamOpenApiDto;
import com.b1nd.dgit.domain.entities.User;
import com.b1nd.dgit.domain.model.http.errors.BadRequestErrorException;
import com.b1nd.dgit.domain.repositories.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  public User save (DodamOpenApiDto dodamOpenApiDto) {
    DodamOpenApiDto.DodamInfoData dodamInfoData = dodamOpenApiDto.getData();
    return userRepository.save(
            User
                    .builder()
                    .id(dodamInfoData.getUniqueId())
                    .name(dodamInfoData.getName())
                    .build()
    );
  }

  @Override
  public User findById(String id) {
    return this.userRepository.findById(id)
            .orElseThrow(() -> BadRequestErrorException.of("존재하지 않는 유저입니다"));
  }
}
