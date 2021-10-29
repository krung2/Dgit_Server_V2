package com.b1nd.dgit.domain.repositories.user;

import com.b1nd.dgit.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {


}
