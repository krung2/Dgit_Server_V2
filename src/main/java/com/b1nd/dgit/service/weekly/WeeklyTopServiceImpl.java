package com.b1nd.dgit.service.weekly;

import com.b1nd.dgit.domain.entities.WeeklyTop;
import com.b1nd.dgit.domain.repositories.week.WeeklyTopRepository;
import com.b1nd.dgit.domain.ro.week.WeeklyRankRo;
import com.b1nd.dgit.service.week.WeekService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeeklyTopServiceImpl {

  private final WeeklyTopRepository weeklyTopRepository;
  private final WeekService weekService;

  public void save () {
    WeeklyRankRo weeklyRankRo = weekService.getWeeklyRanking().get(0);
    weeklyTopRepository.save(WeeklyTop.builder()
            .githubUser(weeklyRankRo.getGithubUser())
            .contribute(weeklyRankRo.getWeeklyCommit())
            .build());
  }
}
