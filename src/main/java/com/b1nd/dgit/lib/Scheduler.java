package com.b1nd.dgit.lib;

import com.b1nd.dgit.domain.entities.GithubUser;
import com.b1nd.dgit.service.github.GithubService;
import com.b1nd.dgit.service.githubUser.GithubUserService;
import com.b1nd.dgit.service.total.TotalTopService;
import com.b1nd.dgit.service.week.WeekService;
import com.b1nd.dgit.service.weekly.WeeklyTopService;
import github.queries.GetContributionQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Scheduler {

  private final GithubUserService githubUserService;
  private final WeekService weekServiceImpl;
  private final WeeklyTopService weeklyTopServiceImpl;
  private final GithubService githubService;
  private final TotalTopService totalTopService;

  @Scheduled(cron = "0 0 8,10,12,14,16,18,20,23 * * *")
  public void weekContributeCheck() {

    System.out.println("-------------n시 스케쥴 시작-------------");

    weekServiceImpl.deleteAllData();
    githubUserService.githubUserList().forEach(githubUser -> {
      try {
        GetContributionQuery.User githubData = githubService.getData(githubUser.getGithubId()).getData().user();
        List<GetContributionQuery.Week> weekCommit = githubData.contributionsCollection().contributionCalendar().weeks();
        if (weekCommit.size() == 0) return;
        List<GetContributionQuery.ContributionDay> contributeDays = weekCommit.get(weekCommit.size() - 1).contributionDays();

        GithubUser updateUser = githubUserService.update(githubUser, githubData);
        contributeDays.forEach(contributeData -> weekServiceImpl.save(updateUser, contributeData));
      } catch (Exception e) {
        e.printStackTrace();
        System.out.println(githubUser.getUser().getName() + " 업데이트 중 오류 발생");
      }
    });

    System.out.println("-------------n시 스케쥴 종료-------------");
  }

  @Scheduled(cron = "0 0 0 * * 1-6")
  public void DailySchedule () {
    System.out.println("-------------일간 스케쥴 시작-------------");
    totalTopService.save();
    System.out.println("-------------일간 스케쥴 종료-------------");
  }

  @Scheduled(cron = "0 0 0 * * 0")
  public void weeklySchedule () {
    System.out.println("-------------주간 스케쥴 시작-------------");
    weeklyTopServiceImpl.save();
    System.out.println("-------------주간 스케쥴 종료-------------");
  }
}
