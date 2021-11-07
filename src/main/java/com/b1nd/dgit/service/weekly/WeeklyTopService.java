package com.b1nd.dgit.service.weekly;

import com.b1nd.dgit.domain.dto.weekly.WeeklyTopListDto;

import java.util.List;

public interface WeeklyTopService {

  void save();

  List<WeeklyTopListDto> findAllData();
}
