package com.b1nd.dgit.domain.repositories.user;

import com.b1nd.dgit.domain.entities.GithubUser;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GithubUserRepository extends JpaRepository<GithubUser, String> {

  List<GithubUser> findAll (Sort sort);
}