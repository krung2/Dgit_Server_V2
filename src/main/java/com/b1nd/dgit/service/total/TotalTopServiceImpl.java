package com.b1nd.dgit.service.total;

import com.b1nd.dgit.domain.entities.GithubUser;
import com.b1nd.dgit.domain.entities.TotalTop;
import com.b1nd.dgit.domain.entities.User;
import com.b1nd.dgit.domain.repositories.top.TotalTopRepository;
import com.b1nd.dgit.domain.ro.user.TotalRankRo;
import com.b1nd.dgit.service.githubUser.GithubUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TotalTopServiceImpl implements TotalTopService {

  private final TotalTopRepository totalTopRepository;
  private final GithubUserService githubUserServiceImpl;

  @Transactional
  public void save() {
    totalTopRepository.save(TotalTop.builder()
            .user(githubUserServiceImpl.getGithubUserListSort().get(0).getUser())
            .date(new Date())
            .build());
  }

  public int getTotalTop(User githubTopUser) {
    return countTopStreak(
            githubTopUser,
            totalTopRepository.findEntityGraph(
                    Sort.by(Sort.Direction.DESC, "date")
            )
    );
  }

  private int countTopStreak (User githubTopUser, List<TotalTop> totalTopList) {
    int streak = 0;
    if (totalTopList.size() == 0) {
      return streak;
    }

    for (TotalTop totalTop : totalTopList) {
      if (totalTop.getUser() == githubTopUser) {
        streak++;
        continue;
      }
      break;
    }

    return streak;
  }

  public TotalRankRo getTotalRank() {
    List<GithubUser> users = githubUserServiceImpl.getGithubUserListSort();

    return TotalRankRo.builder()
            .users(users)
            .totalTop(getTotalTop(users.get(0).getUser()))
            .build();
  }
}
