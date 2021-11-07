package com.b1nd.dgit.domain.repositories.user;

import com.b1nd.dgit.domain.entities.GithubUser;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface GithubUserRepository extends JpaRepository<GithubUser, String> {

  @EntityGraph(attributePaths = "user")
  @Query("select githubUser from GithubUser githubUser")
  List<GithubUser> findEntityGraph();

  @EntityGraph(attributePaths = "user")
  @Query("select githubUser from GithubUser githubUser")
  List<GithubUser> findEntityGraph(Sort sort);

  @Override
  Optional<GithubUser> findById(String githubId);
}