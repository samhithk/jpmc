package com.jpmc.theater;

import com.jpmc.theater.deals.BetweenRangeDeal;
import com.jpmc.theater.deals.DayOfMonthDeal;
import com.jpmc.theater.deals.ShowOfDayDeal;
import com.jpmc.theater.deals.SpecialMovieDeal;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TheaterTests {

  @Test
  void totalFeeForCustomer() {
    Movie spiderMan = new Movie("Spider-Man: No Way Home", "", Duration.ofMinutes(90), true);
    Showing showing =
        new Showing(
            spiderMan,
            12.5,
            LocalDateTime.of(LocalDate.of(2022, Month.MAY, 7), LocalTime.of(11, 30)));
    Theater theater = new Theater(Set.of(showing));
    theater.setDeals(
        List.of(
            new SpecialMovieDeal(.2),
            new ShowOfDayDeal(theater.getSchedule(), 3, 1),
            new ShowOfDayDeal(theater.getSchedule(), 2, 2),
            new DayOfMonthDeal(7, 1)));
    Customer john = new Customer("John Doe");
    Reservation reservation = theater.reserve(john, showing, 4);
    assertEquals(9.5 * 4, reservation.totalFee());
  }

  @Test
  void printMovieSchedule() {
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

    App.printSchedule(theater, false);
  }
}
