package com.b1nd.dgit.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
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

  @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
  @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
  @JsonIgnore
  private GithubUser githubUser;

  @Builder
  public User(String id, String name) {
    this.id = id;
    this.name = name;
  }
}
