package com.jpmc.theater.deals;

import com.jpmc.theater.Showing;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.SortedMap;

public class ShowOfDayDeal implements Deal {
  private final double discount;
  private final int showOfDay;
  private SortedMap<LocalDateTime, Showing> schedule;

  public ShowOfDayDeal(SortedMap<LocalDateTime, Showing> schedule, double discount, int showOfDay) {
    this.schedule = schedule;
    if (discount < 0) throw new IllegalArgumentException("Discount can not be negative");
    this.discount = discount;
    this.showOfDay = showOfDay;
  }

  @Override
  public double calculateTicketPrice(Showing showing) {
    var startTime = showing.getStartTime();
    var scheduleForDay =
        schedule.subMap(
            startTime.toLocalDate().atStartOfDay(), startTime.toLocalDate().atTime(LocalTime.MAX));
    var iterator = scheduleForDay.values().iterator();
    var index = 0;
    while (iterator.hasNext()) {
      var currentShowing = iterator.next();
      if (index + 1 == showOfDay && currentShowing.equals(showing)) {
        return showing.getMovieFee() - discount;
      }
      index++;
    }
    return showing.getMovieFee();
  }
}
