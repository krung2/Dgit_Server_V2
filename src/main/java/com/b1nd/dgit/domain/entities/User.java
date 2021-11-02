package com.b1nd.dgit.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Getter
@NoArgsConstructor
public class User {

  @Id
  private String id;

  @Column(name = "name", nullable = false)
  @NotNull
  private String name;

  @JsonIgnore
  @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
  private GithubUser githubUser;

  @Builder
  public User(String id, String name) {
    this.id = id;
    this.name = name;
  }
}
