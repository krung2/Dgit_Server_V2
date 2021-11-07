package com.b1nd.dgit.domain.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "total_top")
@Getter
@NoArgsConstructor
public class TotalTop {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idx", unique = true, nullable = false)
  private Long idx;

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "fk_user_id")
  private User user;

  @Column(name = "date", nullable = false)
  private Date date;

  @Builder
  public TotalTop(User user, Date date) {
    this.user = user;
    this.date = date;
  }
}
