package com.b1nd.dgit.service.week;

import com.b1nd.dgit.domain.entities.GithubUser;
import com.b1nd.dgit.domain.entities.WeekContribute;
import com.b1nd.dgit.domain.repositories.week.WeekContributeRepository;
import com.b1nd.dgit.domain.ro.week.WeeklyRankRo;
import com.b1nd.dgit.enums.week.WeekDay;
import com.b1nd.dgit.service.githubUser.GithubUserService;
import github.queries.GetContributionQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WeekServiceImpl implements WeekService{

  private final GithubUserService githubUserService;
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

  public void deleteAllData () {
    weekContributeRepository.deleteAll();
  }

  public List<WeekContribute> getAllData () {
    return weekContributeRepository.getAll();
  }

  public List<WeeklyRankRo> getWeeklyRanking () {

    Map<GithubUser, Integer> userToCommit = new HashMap<>();

    githubUserService.githubUserList().forEach(githubUser -> userToCommit.put(githubUser, 0));

    getAllData().forEach(weekContribute -> {
      if(!userToCommit.containsKey(weekContribute.getGithubUser())) {
        userToCommit.put(
                weekContribute.getGithubUser(),
                weekContribute.getContribute()
        );
      } else {
        userToCommit.put(
                weekContribute.getGithubUser(),
                userToCommit.get(weekContribute.getGithubUser()) + weekContribute.getContribute()
        );
      }
    });

    WeeklyRankRo[] weeklyRanks = userToCommit.entrySet().stream()
            .map(data -> new WeeklyRankRo(data.getKey(), data.getValue())).toArray(WeeklyRankRo[]::new);

    return Arrays.stream(weeklyRanks)
            .sorted(Comparator.comparing(WeeklyRankRo::getWeeklyCommit).reversed())
            .collect(Collectors.toList());
  }
}
