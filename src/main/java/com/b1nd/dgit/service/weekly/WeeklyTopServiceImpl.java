package com.b1nd.dgit.service.weekly;

import com.b1nd.dgit.domain.dto.weekly.WeeklyTopListDto;
import com.b1nd.dgit.domain.entities.WeeklyTop;
import com.b1nd.dgit.domain.repositories.week.WeeklyTopRepository;
import com.b1nd.dgit.domain.ro.week.WeeklyRankRo;
import com.b1nd.dgit.service.week.WeekService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WeeklyTopServiceImpl implements WeeklyTopService {

  private final WeeklyTopRepository weeklyTopRepository;
  private final WeekService weekServiceImpl;

  @Transactional
  public void save() {
    WeeklyRankRo weeklyRankRo = weekServiceImpl.getWeeklyRanking().get(0);
    weeklyTopRepository.save(WeeklyTop.builder()
            .githubUser(weeklyRankRo.getGithubUser())
            .contribute(weeklyRankRo.getWeeklyCommit())
            .build());
  }

  @Transactional(readOnly = true)
  public List<WeeklyTopListDto> findAllData() {
    List<WeeklyTopListDto> weeklyTopListDtoList = new ArrayList<>();
    weeklyTopRepository.findEntityGraph(Sort.by(Sort.Direction.DESC, "createdAt"))
            .forEach(weeklyTop -> weeklyTopListDtoList.add(WeeklyTopListDto.of(weeklyTop)));
    return weeklyTopListDtoList;
  }
}
