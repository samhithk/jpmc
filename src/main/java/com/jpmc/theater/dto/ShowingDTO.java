package com.jpmc.theater.dto;

import com.jpmc.theater.Showing;

import java.time.Duration;
import java.time.LocalDateTime;

public class ShowingDTO {
  private LocalDateTime startTime;
  private String title;
  private Duration runningTime;
  private double movieFee;

  public ShowingDTO(Showing showing) {
    startTime = showing.getStartTime();
    title = showing.getMovie().getTitle();
    runningTime = showing.getMovie().getRunningTime();
    movieFee = showing.getMovieFee();
  }
}
