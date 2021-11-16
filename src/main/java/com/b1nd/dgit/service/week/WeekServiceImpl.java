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
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WeekServiceImpl implements WeekService {

  private final GithubUserService githubUserService;
  private final WeekContributeRepository weekContributeRepository;

  @Transactional
  public WeekContribute save(GithubUser githubUser, GetContributionQuery.ContributionDay contributionData) {
    return weekContributeRepository.save(weekContributeDataToEntity(githubUser, contributionData));
  }

  private WeekContribute weekContributeDataToEntity(GithubUser githubUser, GetContributionQuery.ContributionDay contributionData) {
    return WeekContribute.builder()
            .githubUser(githubUser)
            .contribute(contributionData.contributionCount())
            .date(contributionData.date())
            .weekDay(WeekDay.values()[contributionData.weekday()])
            .build();
  }

  @Transactional
  public void deleteAllData() {
    weekContributeRepository.deleteAll();
  }

  @Transactional(readOnly = true)
  public List<WeekContribute> getAllData() {
    return weekContributeRepository.findAll();
  }

  @Transactional(readOnly = true)
  public List<WeeklyRankRo> getWeeklyRanking() {

    Map<GithubUser, Integer> userToCommit = new HashMap<>();
    Map<String, Integer> userIdWeekCommit = new HashMap<>();

    githubUserService.getGithubUserList().forEach(githubUser -> userToCommit.put(githubUser, 0));
    getAllData().forEach(weekContribute -> getWeeklyContribute(weekContribute, userIdWeekCommit));

    return weeklyRankRos(userToCommit, userIdWeekCommit).stream()
            .sorted(Comparator.comparing(WeeklyRankRo::getWeeklyCommit).reversed())
            .collect(Collectors.toList());
  }

  private void getWeeklyContribute(WeekContribute weekContribute, Map<String, Integer> userIdWeekCommit) {
      userIdWeekCommit.put(
              weekContribute.getGithubUser().getGithubId(),
              userIdWeekCommit.getOrDefault(weekContribute.getGithubUser().getGithubId(), 0) + weekContribute.getContribute()
      );
  }

  private List<WeeklyRankRo> weeklyRankRos(Map<GithubUser, Integer> userToCommit, Map<String, Integer> userIdWeekCommit) {
    return Arrays.asList(userToCommit.entrySet().stream()
            .map(data -> WeeklyRankRo.of(
                    data.getKey(),
                    userIdWeekCommit.getOrDefault(data.getKey().getGithubId(), data.getValue())
                    )
            ).toArray(WeeklyRankRo[]::new));
  }
}
