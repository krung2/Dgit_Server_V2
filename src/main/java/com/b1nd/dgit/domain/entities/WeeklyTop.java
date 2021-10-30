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
  @JoinColumn(name = "fk_user_id")
  private User user;

  @Column(name = "contribute", nullable = false)
  private Long contribute;

  @Column(name = "date", nullable = false)
  private Date date;

  @Builder
  public WeeklyTop (User user, Long contribute, Date date) {
    this.user = user;
    this.contribute = contribute;
    this.date = date;
  }
}