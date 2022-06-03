package com.jpmc.theater.deals;

import com.jpmc.theater.Showing;

public class SpecialMovieDeal implements Deal {
  private double percent;

  public SpecialMovieDeal(double percent) {
    if (percent > 1 || percent < 0)
      throw new IllegalArgumentException("Percent must be between 0 and 1.");
    this.percent = percent;
  }

  @Override
  public double calculateTicketPrice(Showing showing) {
    if (showing.getMovie().isSpecial()) return showing.getMovieFee() * (1 - percent);
    return showing.getMovieFee();
  }
}
