package com.b1nd.dgit.domain.dto.user;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class ModifyGithubDto {

  @NotBlank(message = "githubIdsms 필수 입력값입니다")
  private String githubId;

  public ModifyGithubDto(String githubId) {
    this.githubId = githubId;
  }
}
