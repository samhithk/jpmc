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

public class DealTests {

  @Test
  void specialMovieWith20PercentDiscount() {
    Movie spiderMan = new Movie("Spider-Man: No Way Home", "", Duration.ofMinutes(90), true);
    Showing showing =
        new Showing(spiderMan, 12.5, LocalDateTime.of(LocalDate.now(), LocalTime.now()));
    Theater theater = new Theater(Set.of(showing));
    theater.setDeals(List.of(new SpecialMovieDeal(.2)));
    assertEquals(10, theater.calculateTicketPrice(showing));
  }

  @Test
  void firstShowingOfDay3DollarDiscount() {
    Movie spiderMan = new Movie("Spider-Man: No Way Home", "", Duration.ofMinutes(90), true);
    Showing showing =
        new Showing(spiderMan, 12.5, LocalDateTime.of(LocalDate.now(), LocalTime.now()));
    Theater theater = new Theater(Set.of(showing));
    theater.setDeals(List.of(new ShowOfDayDeal(theater.getSchedule(), 3, 1)));
    assertEquals(9.50, theater.calculateTicketPrice(showing));
  }

  @Test
  void secondShowingOfDay2DollarDiscount() {
    Movie spiderMan = new Movie("Spider-Man: No Way Home", "", Duration.ofMinutes(90), true);
    var theBatMan =
        new Movie(
            "The Batman",
            "When a sadistic serial killer begins murdering key political figures in Gotham, Batman is forced to investigate the city's hidden corruption and question his family's involvement.",
            Duration.ofMinutes(95),
            false);
    Showing theBatManShowing =
        new Showing(theBatMan, 9.5, LocalDateTime.of(LocalDate.now(), LocalTime.now()));
    Showing spiderManShowing =
        new Showing(
            spiderMan, 12.5, LocalDateTime.of(LocalDate.now(), LocalTime.now().plusMinutes(1)));
    Theater theater = new Theater(Set.of(theBatManShowing, spiderManShowing));
    theater.setDeals(List.of(new ShowOfDayDeal(theater.getSchedule(), 2, 2)));
    assertEquals(10.50, theater.calculateTicketPrice(spiderManShowing));
  }

  @Test
  void showingBetween11amAnd4pm25PercentDiscount() {
    Movie spiderMan = new Movie("Spider-Man: No Way Home", "", Duration.ofMinutes(90), true);
    Showing showing =
        new Showing(spiderMan, 12.5, LocalDateTime.of(LocalDate.now(), LocalTime.of(11, 30)));
    Theater theater = new Theater(Set.of(showing));
    theater.setDeals(List.of(new BetweenRangeDeal(LocalTime.of(11, 0), LocalTime.of(16, 0), .25)));
    assertEquals(9.38, theater.calculateTicketPrice(showing));
  }

  @Test
  void showingOn7th1DollarDiscount() {
    Movie spiderMan = new Movie("Spider-Man: No Way Home", "", Duration.ofMinutes(90), true);
    Showing showing =
        new Showing(
            spiderMan,
            12.5,
            LocalDateTime.of(LocalDate.of(2022, Month.MAY, 7), LocalTime.of(11, 30)));
    Theater theater = new Theater(Set.of(showing));
    theater.setDeals(List.of(new DayOfMonthDeal(7, 1)));
    assertEquals(11.5, theater.calculateTicketPrice(showing));
  }

  @Test
  void biggestDiscountWins() {
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
            new BetweenRangeDeal(LocalTime.of(11, 0), LocalTime.of(16, 0), .25),
            new DayOfMonthDeal(7, 1)));
    assertEquals(9.38, theater.calculateTicketPrice(showing));
  }
}
