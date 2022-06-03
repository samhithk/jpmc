package com.jpmc.theater.dto;

import com.jpmc.theater.Theater;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TheaterDTO {
  private Map<LocalDate, List<ShowingDTO>> schedule;

  public TheaterDTO(Theater theater) {
    schedule = new HashMap<>();
    theater
        .getSchedule()
        .forEach(
            (dateTime, showing) -> {
              var date = dateTime.toLocalDate();
              if (!schedule.containsKey(date)) {
                schedule.put(date, new ArrayList<>());
              }
              schedule.get(date).add(new ShowingDTO(showing));
            });
  }
}
