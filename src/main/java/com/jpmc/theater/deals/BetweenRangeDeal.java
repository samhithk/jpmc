package com.jpmc.theater.deals;

import com.jpmc.theater.Showing;

import java.time.LocalTime;

public class BetweenRangeDeal implements Deal {
  private LocalTime startTime;
  private LocalTime endTime;
  private double percent;

  public BetweenRangeDeal(LocalTime startTime, LocalTime endTime, double percent) {
    this.startTime = startTime;
    this.endTime = endTime;
    if (percent > 1 || percent < 0)
      throw new IllegalArgumentException("Percent must be between 0 and 1.");
    this.percent = percent;
  }

  @Override
  public double calculateTicketPrice(Showing showing) {
    var showTime = showing.getStartTime().toLocalTime();
    if ((showTime.equals(startTime) || showTime.isAfter(startTime))
        && (showTime.equals(endTime) || showTime.isBefore(endTime))) {
      return showing.getMovieFee() * (1 - percent);
    }
    return showing.getMovieFee();
  }
}
