package com.b1nd.dgit.service.week;

import com.b1nd.dgit.domain.dto.week.WeekSaveDto;
import com.b1nd.dgit.domain.entities.GithubUser;
import com.b1nd.dgit.domain.entities.WeekContribute;
import com.b1nd.dgit.domain.repositories.week.WeekContributeRepository;
import github.queries.GetContributionQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeekServiceImpl implements WeekService{

  private final WeekContributeRepository weekContributeRepository;

  public WeekContribute save(GithubUser githubUser, WeekSaveDto weekSaveDto) {
    return weekContributeRepository.save(WeekContribute.builder()
            .githubUser(githubUser)
            .contribute(weekSaveDto.getContribute())
            .date(weekSaveDto.getDate())
            .weekDay(weekSaveDto.getWeekDay())
            .build()
    );
  }
}
