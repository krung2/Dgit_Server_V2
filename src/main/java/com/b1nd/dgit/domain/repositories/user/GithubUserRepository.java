package com.b1nd.dgit.domain.repositories.user;

import com.b1nd.dgit.domain.entities.GithubUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GithubUserRepository extends JpaRepository<GithubUser, String> {
}