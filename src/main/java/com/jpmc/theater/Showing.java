package com.jpmc.theater;

import java.time.LocalDateTime;
import java.util.Objects;

public class Showing {
  private Movie movie;
  private double ticketPrice;
  private LocalDateTime showStartTime;

  public Showing(Movie movie, double ticketPrice, LocalDateTime showStartTime) {
    this.movie = movie;
    this.ticketPrice = ticketPrice;
    this.showStartTime = showStartTime;
  }

  public Movie getMovie() {
    return movie;
  }

  public LocalDateTime getStartTime() {
    return showStartTime;
  }

  public double getMovieFee() {
    return ticketPrice;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Showing)) return false;
    Showing showing = (Showing) o;
    return Double.compare(showing.ticketPrice, ticketPrice) == 0 && movie.equals(showing.movie) && showStartTime.equals(showing.showStartTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(movie, ticketPrice, showStartTime);
  }
}
