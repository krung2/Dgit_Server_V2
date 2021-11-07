package com.b1nd.dgit.domain.dto.week;

import com.b1nd.dgit.enums.week.WeekDay;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class WeekSaveDto {

  private int contribute;
  private Date date;
  private WeekDay weekDay;


  public WeekSaveDto(int contribute, Date date, WeekDay weekDay) {
    this.contribute = contribute;
    this.date = date;
    this.weekDay = weekDay;
  }
}
