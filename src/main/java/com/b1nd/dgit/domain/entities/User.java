package com.b1nd.dgit.domain.entities;

import lombok.*;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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

  @Column(name = "github", nullable = true)
  private String github;

  @Builder
  public User(String id, String name, String github) {
    this.id = id;
    this.name = name;
    this.github = github;
  }

  public User update(String github) {
    this.github = github;

    return this;
  }
}
