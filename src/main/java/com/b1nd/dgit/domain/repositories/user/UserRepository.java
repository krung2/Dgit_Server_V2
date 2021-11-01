package com.b1nd.dgit.domain.repositories.user;

import com.b1nd.dgit.domain.entities.User;
import com.sun.istack.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

  Optional<User> findById(String id);
}
