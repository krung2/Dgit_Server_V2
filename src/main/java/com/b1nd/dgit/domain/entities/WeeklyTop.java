package com.b1nd.dgit.domain.entities;

import com.b1nd.dgit.enums.week.WeekDay;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "weekly_top")
@NoArgsConstructor
public class WeeklyTop extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idx", unique = true, nullable = false)
  private Long idx;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "github_id")
  private GithubUser githubUser;

  @Column(name = "contribute", nullable = false)
  private int contribute;

  @Column(name = "date", nullable = false)
  private Date date;

  @Builder
  public WeeklyTop (GithubUser githubUser, int contribute, Date date) {
    this.githubUser = githubUser;
    this.contribute = contribute;
    this.date = date;
  }
}
