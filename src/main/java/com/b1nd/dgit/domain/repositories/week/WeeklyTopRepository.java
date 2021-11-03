package com.b1nd.dgit.domain.repositories.week;

import com.b1nd.dgit.domain.entities.TotalTop;
import com.b1nd.dgit.domain.entities.WeeklyTop;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WeeklyTopRepository extends JpaRepository<WeeklyTop, Long> {

  @EntityGraph(attributePaths = "githubUser")
  @Query("select weeklyTop from WeeklyTop weeklyTop")
  List<WeeklyTop> findEntityGraph (Sort sort);
}
