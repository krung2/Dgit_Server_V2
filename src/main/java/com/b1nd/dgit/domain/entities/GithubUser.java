package com.b1nd.dgit.domain.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
  @Column(name = "github_id", unique = true, nullable = false)
  private String githubId;

  @JsonManagedReference
  @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
  @JoinColumn(name = "fk_user_id")
  private User user;

  @Column(name = "total_contributions", nullable = false)
  private int totalContributions;

  @Column(name = "user_image", nullable = true)
  private String userImage;

  @Column(name = "bio", nullable = true)
  private String bio;

  @Builder
  public GithubUser(String githubId, User user, int totalContributions, String userImage, String bio){
    this.githubId = githubId;
    this.user = user;
    this.totalContributions = totalContributions;
    this.userImage = userImage;
    this.bio = bio;
  }
}
