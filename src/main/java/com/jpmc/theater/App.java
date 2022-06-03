package com.jpmc.theater;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jpmc.theater.deals.BetweenRangeDeal;
import com.jpmc.theater.deals.DayOfMonthDeal;
import com.jpmc.theater.deals.ShowOfDayDeal;
import com.jpmc.theater.deals.SpecialMovieDeal;
import com.jpmc.theater.dto.TheaterDTO;
import com.jpmc.theater.serialization.DurationSerializer;
import com.jpmc.theater.serialization.LocalDateTimeSerializer;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class App {

  private static Gson gson =
      new GsonBuilder()
          .setPrettyPrinting()
          .registerTypeAdapter(LocalDate.class, new LocalDateTimeSerializer())
          .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer())
          .registerTypeAdapter(Duration.class, new DurationSerializer())
          .create();

  public static void main(String[] args) {
    var spiderMan =
        new Movie(
            "Spider-Man: No Way Home",
            "With Spider-Man's identity now revealed, Peter asks Doctor Strange for help. When a spell goes wrong, dangerous foes from other worlds start to appear, forcing Peter to discover what it truly means to be Spider-Man.",
            Duration.ofMinutes(90),
            true);
    var turningRed =
        new Movie(
            "Turning Red",
            "A 13-year-old girl named Meilin turns into a giant red panda whenever she gets too excited.",
            Duration.ofMinutes(85),
            false);
    var theBatMan =
        new Movie(
            "The Batman",
            "When a sadistic serial killer begins murdering key political figures in Gotham, Batman is forced to investigate the city's hidden corruption and question his family's involvement.",
            Duration.ofMinutes(95),
            false);

    Theater theater =
        new Theater(
            Set.of(
                new Showing(turningRed, 11, LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 0))),
                new Showing(
                    spiderMan, 12.5, LocalDateTime.of(LocalDate.now(), LocalTime.of(11, 0))),
                new Showing(theBatMan, 9, LocalDateTime.of(LocalDate.now(), LocalTime.of(12, 50))),
                new Showing(
                    turningRed, 11, LocalDateTime.of(LocalDate.now(), LocalTime.of(14, 30))),
                new Showing(
                    spiderMan, 12.5, LocalDateTime.of(LocalDate.now(), LocalTime.of(16, 10))),
                new Showing(
                    theBatMan,
                    9,
                    LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(17, 50))),
                new Showing(
                    turningRed,
                    11,
                    LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(19, 30))),
                new Showing(
                    spiderMan,
                    12.5,
                    LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(21, 10))),
                new Showing(
                    theBatMan,
                    9,
                    LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(23, 0)))));

    theater.setDeals(
        List.of(
            new SpecialMovieDeal(.2),
            new ShowOfDayDeal(theater.getSchedule(), 3, 1),
            new ShowOfDayDeal(theater.getSchedule(), 2, 2),
            new BetweenRangeDeal(LocalTime.of(11, 0), LocalTime.of(16, 0), 0.25),
            new DayOfMonthDeal(7, 1)));

    printSchedule(theater, true);
  }

  public static void printSchedule(Theater theater, boolean isJson) {
    if (isJson) printJson(theater);
    else printSimpleTextSchedule(theater.getSchedule());
  }

  private static void printJson(Theater theater) {
    var dto = new TheaterDTO(theater);
    var jsonString = gson.toJson(dto);
    System.out.println(jsonString);
  }

  private static void printSimpleTextSchedule(Map<LocalDateTime, Showing> schedule) {
    System.out.println("===================================================");
    var iterator = schedule.values().iterator();
    int sequence = 0;
    Showing prev = null;
    while (iterator.hasNext()) {
      var showing = iterator.next();
      if (prev == null
          || !prev.getStartTime().toLocalDate().isEqual(showing.getStartTime().toLocalDate())) {
        System.out.println(showing.getStartTime().toLocalDate());
        sequence = 1;
      }
      System.out.println(
          sequence
              + ": "
              + showing.getStartTime()
              + " "
              + showing.getMovie().getTitle()
              + " "
              + humanReadableFormat(showing.getMovie().getRunningTime())
              + " $"
              + showing.getMovieFee());
      prev = showing;
      sequence++;
    }
    System.out.println("===================================================");
  }

  private static String humanReadableFormat(Duration duration) {
    long hour = duration.toHours();
    long remainingMin = duration.toMinutes() - TimeUnit.HOURS.toMinutes(duration.toHours());

    return String.format(
        "(%s hour%s %s minute%s)",
        hour, handlePlural(hour), remainingMin, handlePlural(remainingMin));
  }

  // (s) postfix should be added to handle plural correctly
  private static String handlePlural(long value) {
    if (value == 1) {
      return "";
    } else {
      return "s";
    }
  }
}
