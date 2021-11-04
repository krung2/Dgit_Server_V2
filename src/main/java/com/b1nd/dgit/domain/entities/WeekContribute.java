package com.b1nd.dgit.domain.entities;

import com.b1nd.dgit.enums.week.WeekDay;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "week_contribute")
@Getter
@NoArgsConstructor
public class WeekContribute {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idx", unique = true, nullable = false)
  private Long idx;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "github_id")
  private GithubUser githubUser;

  @Column(name = "contribute", nullable = false)
  private int contribute;

  @Column(name = "date" , nullable = false)
  private Date date;

  @Column(name = "weekday", nullable = false)
  private WeekDay weekDay;

  @Builder
  public WeekContribute (GithubUser githubUser, int contribute, Object date, WeekDay weekDay) {
    this.githubUser = githubUser;
    this.contribute = contribute;
    try {
      this.date = new SimpleDateFormat("yyyy-MM-dd").parse(date.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
    this.weekDay = weekDay;
  }
}
