package com.jpmc.theater;

import com.jpmc.theater.deals.Deal;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.*;

public class Theater {
  private SortedMap<LocalDateTime, Showing> schedule = new TreeMap<>();
  private Set<Showing> showings;
  private List<Deal> deals;
  private Set<Reservation> reservations;

  public Theater(Set<Showing> showings) {
    this.reservations = new HashSet<>();
    this.showings = showings;
    this.deals = new ArrayList<>();
    showings.forEach(s -> schedule.put(s.getStartTime(), s));
  }

  public double calculateTicketPrice(Showing showing) {
    double price = showing.getMovieFee();
    for (var deal : deals) {
      price = Math.min(price, deal.calculateTicketPrice(showing));
    }
    var bd = BigDecimal.valueOf(price);
    return bd.setScale(2, RoundingMode.HALF_UP).doubleValue();
  }

  public Reservation reserve(Customer customer, Showing showing, int numTickets) {
    if (!showings.contains(showing))
      throw new IllegalStateException("Showing not part of schedule");
    var reservation = new Reservation(customer, showing, numTickets, calculateTicketPrice(showing));
    reservations.add(reservation);
    return reservation;
  }

  public SortedMap<LocalDateTime, Showing> getSchedule() {
    return schedule;
  }

  public void setDeals(List<Deal> deals) {
    this.deals = deals;
  }

  public Set<Reservation> getReservations() {
    return reservations;
  }
}
