package com.b1nd.dgit.domain.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "github_user")
@Getter
@NoArgsConstructor
public class GithubUser extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idx", unique = true, nullable = false)
  private Long idx;

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "fk_user_id")
  private User user;

  @Column(name = "total_contributions", nullable = false)
  private Long totalContributions;

  @Column(name = "user_image", nullable = true)
  private String userImage;

  @Column(name = "bio", nullable = true)
  private String bio;

  @Builder
  public GithubUser(User user, Long totalContributions, String userImage, String bio){
    this.user = user;
    this.totalContributions = totalContributions;
    this.userImage = userImage;
    this.bio = bio;
  }

  public GithubUser update(Long totalContributions, String userImage, String bio){
    this.totalContributions = totalContributions;
    this.userImage = userImage;
    this.bio = bio;

    return this;
  }
}
