package com.b1nd.dgit.domain.dto.weekly;

import com.b1nd.dgit.domain.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class WeeklyTopUser {
  
  private String id;
  private String name;

  public static WeeklyTopUser of(User user) {
    return WeeklyTopUser.builder()
            .id(user.getId())
            .name(user.getName())
            .build();
  }
}
