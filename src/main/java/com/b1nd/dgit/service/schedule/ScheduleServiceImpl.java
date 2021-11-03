package com.b1nd.dgit.service.schedule;

import com.b1nd.dgit.domain.entities.GithubUser;
import com.b1nd.dgit.service.github.GithubService;
import com.b1nd.dgit.service.githubUser.GithubUserService;
import com.b1nd.dgit.service.week.WeekService;
import com.b1nd.dgit.service.weekly.WeeklyTopService;
import github.queries.GetContributionQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

  private final GithubUserService githubUserService;
  private final WeekService weekServiceImpl;
  private final WeeklyTopService weeklyTopServiceImpl;
  private final GithubService githubService;

  public void weekContributeCheck() {
    weekServiceImpl.deleteAllData();

    githubUserService.githubUserList().forEach(githubUser -> {
      GetContributionQuery.User githubData = githubService.getData(githubUser.getGithubId()).getData().user();
      List<GetContributionQuery.Week> weekCommit = githubData.contributionsCollection().contributionCalendar().weeks();
      List<GetContributionQuery.ContributionDay> contributeDays = weekCommit.get(weekCommit.size() - 1).contributionDays();

      GithubUser updateUser = githubUserService.update(githubUser, githubData);
      contributeDays.forEach(contributeData -> weekServiceImpl.save(updateUser, contributeData));
    });
  }

  public void DailySchedule () {

  }

  public void weeklySchedule () {
    weeklyTopServiceImpl.save();
  }
}