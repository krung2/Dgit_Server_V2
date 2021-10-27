package com.b1nd.dgit.domain.entities;

import com.b1nd.dgit.enums.week.WeekDay;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
  @JoinColumn(name = "fk_user_id")
  private User user;

  @Column(name = "contribute", nullable = false)
  private Long contribute;

  @Column(name = "date", nullable = false)
  private Date date;

  @Column(name = "weekday", nullable = false)
  private WeekDay weekContribute;

}
