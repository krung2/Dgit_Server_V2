package com.b1nd.dgit.service.weekly;

import com.b1nd.dgit.domain.entities.WeeklyTop;

import java.util.List;

public interface WeeklyTopService {

  void save ();
  List<WeeklyTop> findAllData ();
}
