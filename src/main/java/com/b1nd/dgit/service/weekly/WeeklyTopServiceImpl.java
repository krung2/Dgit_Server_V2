package com.b1nd.dgit.service.weekly;

import com.b1nd.dgit.domain.dto.weekly.WeeklyTopGithubUser;
import com.b1nd.dgit.domain.dto.weekly.WeeklyTopListDto;
import com.b1nd.dgit.domain.dto.weekly.WeeklyTopUser;
import com.b1nd.dgit.domain.entities.WeeklyTop;
import com.b1nd.dgit.domain.repositories.week.WeeklyTopRepository;
import com.b1nd.dgit.domain.ro.week.WeeklyRankRo;
import com.b1nd.dgit.service.week.WeekService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WeeklyTopServiceImpl implements WeeklyTopService {

  private final WeeklyTopRepository weeklyTopRepository;
  private final WeekService weekService;

  public void save () {
    WeeklyRankRo weeklyRankRo = weekService.getWeeklyRanking().get(0);
    weeklyTopRepository.save(WeeklyTop.builder()
            .githubUser(weeklyRankRo.getGithubUser())
            .contribute(weeklyRankRo.getWeeklyCommit())
            .build());
  }

  public List<WeeklyTopListDto> findAllData () {
    List<WeeklyTopListDto> weeklyTopListDtoList = new ArrayList<>();
    weeklyTopRepository.findEntityGraph(Sort.by(Sort.Direction.DESC, "createdAt"))
            .forEach(weeklyTop -> weeklyTopListDtoList.add(WeeklyTopListDto.of(weeklyTop)));
     return weeklyTopListDtoList;
  }
}
