package org.taxcalculator.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuantityTest {
  @Test
  void addQuantityWhenNameNotGiven() {
    try {
      Validator.addQuantity(false, false, "100");
      fail("No Exception Thrown");
    } catch (Exception e) {
      assertEquals("Please enter item name first", e.getMessage(), "Wrong Exception Thrown");
    }
  }

  @Test
  void addQuantityWhenNameQuantityAlreadyGiven() {
    try {
      Validator.addQuantity(true, true, "100");
      fail("No Exception Thrown");
    } catch (Exception e) {
      assertEquals("Item quantity already received", e.getMessage(), "Wrong Exception Thrown");
    }
  }

  @Test
  void addQuantityWhenNameGivenQuantityInvalid() {
    try {
      Validator.addQuantity(true, false, "-100");
      fail("No Exception Thrown");
    } catch (Exception e) {
      assertEquals("Please enter valid quantity", e.getMessage(), "Wrong Exception Thrown");
    }
  }

  @Test
  void addQuantityWhenNameGivenQuantityValid() {
    assertAll(() -> assertEquals(100, Validator.addQuantity(true, false, "100")));
  }
}
