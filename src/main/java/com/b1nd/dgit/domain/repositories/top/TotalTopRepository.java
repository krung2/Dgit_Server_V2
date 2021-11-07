package com.b1nd.dgit.domain.repositories.top;

import com.b1nd.dgit.domain.entities.TotalTop;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TotalTopRepository extends JpaRepository<TotalTop, Long> {

  @EntityGraph(attributePaths = "user")
  @Query("select totalTop from TotalTop totalTop")
  List<TotalTop> findEntityGraph(Sort sort);
}
