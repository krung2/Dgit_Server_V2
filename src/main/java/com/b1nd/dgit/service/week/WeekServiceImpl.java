package com.b1nd.dgit.service.week;

import com.b1nd.dgit.domain.repositories.week.WeekContributeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeekServiceImpl implements WeekService{

  private final WeekContributeRepository weekContributeRepository;
}