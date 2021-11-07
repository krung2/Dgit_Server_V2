package com.b1nd.dgit.domain.repositories.week;

import com.b1nd.dgit.domain.entities.WeekContribute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WeekContributeRepository extends JpaRepository<WeekContribute, Long> {

  void deleteAll();

  List<WeekContribute> findAll();
}