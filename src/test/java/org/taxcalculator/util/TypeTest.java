package org.taxcalculator.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TypeTest {
  @Test
  void addTypeWhenNameNotGiven() {
    try {
      Validator.addType(false, false, "raw");
      fail("No Exception Thrown");
    } catch (Exception e) {
      assertEquals("Please enter item name first", e.getMessage(), "Wrong Exception Thrown");
    }
  }

  @Test
  void addTypeWhenNameTypeAlreadyGiven() {
    try {
      Validator.addType(true, true, "raw");
      fail("No Exception Thrown");
    } catch (Exception e) {
      assertEquals("Item type already received", e.getMessage(), "Wrong Exception Thrown");
    }
  }

  @Test
  void addTypeWhenNameGivenTypeInvalid() {
    try {
      Validator.addType(true, false, "r");
      fail("No Exception Thrown");
    } catch (Exception e) {
      assertEquals("Input valid type of item", e.getMessage(), "Wrong Exception Thrown");
    }
  }

  @Test
  void addTypeWhenNameGivenTypeValid() {
    assertAll(() -> assertEquals("RAW", Validator.addType(true, false, "raw")));
  }
}
