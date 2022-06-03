package com.jpmc.theater.deals;

import com.jpmc.theater.Showing;

public class DayOfMonthDeal implements Deal {
  private int dayOfMonth;
  private double discount;

  public DayOfMonthDeal(int dayOfMonth, double discount) {
    this.dayOfMonth = dayOfMonth;
    if (discount < 0) throw new IllegalArgumentException("Discount can not be negative");
    this.discount = discount;
  }

  @Override
  public double calculateTicketPrice(Showing showing) {
    if (showing.getStartTime().getDayOfMonth() == dayOfMonth) {
      return showing.getMovieFee() - discount;
    }
    return showing.getMovieFee();
  }
}
