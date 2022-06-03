package com.jpmc.theater;

import java.util.Objects;

public class Customer {

  private static long lastId = 0;

  private String name;
  private long id;

  /**
   * @param name customer name
   */
  public Customer(String name) {
    this.id = ++id;
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Customer)) return false;
    Customer customer = (Customer) o;
    return Objects.equals(name, customer.name) && Objects.equals(id, customer.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, id);
  }

  @Override
  public String toString() {
    return "name: " + name;
  }
}
