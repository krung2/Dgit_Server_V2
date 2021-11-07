package com.b1nd.dgit.domain.dto.dodam;

import com.b1nd.dgit.domain.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
public class DodamOpenApiDto extends RestBaseDto {

  private final DodamInfoData data;

  public DodamOpenApiDto(int status, String message, DodamInfoData data) {
    super(status, message);
    this.data = new DodamInfoData(data);
  }

  @Getter
  @Builder
  @AllArgsConstructor
  public static class DodamInfoData {

    private final String uniqueId;
    private final int grade;
    private final int room;
    private final int number;
    private final String name;
    private final String email;
    private final String profileImage;
    private final int accessLevel;

    public DodamInfoData(DodamInfoData data) {
      this.uniqueId = data.getUniqueId();
      this.grade = data.getGrade();
      this.room = data.getRoom();
      this.number = data.getNumber();
      this.name = data.getName();
      this.email = data.getEmail();
      this.profileImage = data.getProfileImage();
      this.accessLevel = data.getAccessLevel();
    }

    public static User toEntity(DodamInfoData data) {
      return User.builder()
              .id(data.getUniqueId())
              .name(data.getName())
              .build();
    }
  }
}
