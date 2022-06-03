package com.jpmc.theater.deals;

import com.jpmc.theater.Showing;

public interface Deal {
  double calculateTicketPrice(Showing showing);
}
