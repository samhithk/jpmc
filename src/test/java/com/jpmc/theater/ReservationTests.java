package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.*;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReservationTests {

  @Test
  void totalFee() {
    var customer = new Customer("John Doe");
    Movie spiderMan = new Movie("Spider-Man: No Way Home", "", Duration.ofMinutes(90), true);
    Showing showing =
        new Showing(
            spiderMan,
            12.5,
            LocalDateTime.of(LocalDate.of(2022, Month.MAY, 7), LocalTime.of(11, 30)));
    Theater theater = new Theater(Set.of(showing));
    var reservation = theater.reserve(customer, showing, 1);
    assertTrue(theater.getReservations().contains(reservation));
  }
}
