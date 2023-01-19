package org.taxcalculator.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PriceTest {
  @Test
  void addPriceWhenNameNotGiven() {
    try {
      Validator.addPrice(false, false, "100");
      fail("No Exception Thrown");
    } catch (Exception e) {
      assertEquals("Please enter item name first", e.getMessage(), "Wrong Exception Thrown");
    }
  }

  @Test
  void addPriceWhenNamePriceAlreadyGiven() {
    try {
      Validator.addPrice(true, true, "100");
      fail("No Exception Thrown");
    } catch (Exception e) {
      assertEquals("Item Price already received", e.getMessage(), "Wrong Exception Thrown");
    }
  }

  @Test
  void addPriceWhenNameGivenPriceInvalid() {
    try {
      Validator.addPrice(true, false, "-100");
      fail("No Exception Thrown");
    } catch (Exception e) {
      assertEquals("Please enter valid price", e.getMessage(), "Wrong Exception Thrown");
    }
  }

  @Test
  void addPriceWhenNameGivenPriceValid() {
    assertAll(() -> assertEquals(100.0, Validator.addPrice(true, false, "100")));
  }
}
