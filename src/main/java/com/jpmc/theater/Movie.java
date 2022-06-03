package com.jpmc.theater;

import java.time.Duration;
import java.util.Objects;

public class Movie {

  private String title;
  private String description;
  private Duration runningTime;
  private boolean isSpecial;

  public Movie(String title, String description, Duration runningTime, boolean isSpecial) {
    this.title = title;
    this.description = description;
    this.runningTime = runningTime;
    this.isSpecial = isSpecial;
  }

  public String getTitle() {
    return title;
  }

  public Duration getRunningTime() {
    return runningTime;
  }

  public String getDescription() {
    return description;
  }

  public boolean isSpecial() {
    return isSpecial;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Movie)) return false;
    Movie movie = (Movie) o;
    return isSpecial == movie.isSpecial
        && Objects.equals(title, movie.title)
        && Objects.equals(description, movie.description)
        && Objects.equals(runningTime, movie.runningTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, description, runningTime, isSpecial);
  }
}
