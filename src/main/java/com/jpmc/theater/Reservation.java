package com.jpmc.theater;

import java.util.Objects;

public class Reservation {
  private Customer customer;
  private Showing showing;
  private int audienceCount;
  private double pricePerTicket;

  public Reservation(Customer customer, Showing showing, int audienceCount, double pricePerTicket) {
    this.customer = customer;
    this.showing = showing;
    this.audienceCount = audienceCount;
    this.pricePerTicket = pricePerTicket;
  }

  public double totalFee() {
    return pricePerTicket * audienceCount;
  }

  public Customer getCustomer() {
    return customer;
  }

  public Showing getShowing() {
    return showing;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Reservation)) return false;
    Reservation that = (Reservation) o;
    return audienceCount == that.audienceCount
        && Double.compare(that.pricePerTicket, pricePerTicket) == 0
        && customer.equals(that.customer)
        && showing.equals(that.showing);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customer, showing, audienceCount, pricePerTicket);
  }
}
