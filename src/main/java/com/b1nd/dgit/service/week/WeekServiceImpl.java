package com.b1nd.dgit.service.week;

import com.b1nd.dgit.domain.entities.GithubUser;
import com.b1nd.dgit.domain.entities.WeekContribute;
import com.b1nd.dgit.domain.repositories.week.WeekContributeRepository;
import com.b1nd.dgit.enums.week.WeekDay;
import github.queries.GetContributionQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeekServiceImpl implements WeekService{

  private final WeekContributeRepository weekContributeRepository;

  public WeekContribute save(GithubUser githubUser, GetContributionQuery.ContributionDay contributionData) {
    return weekContributeRepository.save(WeekContribute.builder()
            .githubUser(githubUser)
            .contribute(contributionData.contributionCount())
            .date(contributionData.date())
            .weekDay(WeekDay.values()[contributionData.weekday()])
            .build()
    );
  }
}
