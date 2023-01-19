package org.taxcalculator.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NameTest {
  @Test
  void checkEmptyLineWhenItemTypeNotGiven() {
    try {
      Validator.checkEmptyLine("", true, false);
      fail("No Exception Thrown expected Enter item type Exception");
    } catch (Exception e) {
      assertEquals("Enter item type", e.getMessage(), "Wrong Exception Thrown");
    }
  }

  @Test
  void checkEmptyLineWhenNameTypeGiven() {
    assertAll(() -> assertEquals(false, Validator.checkEmptyLine("", true, true)));
  }

  @Test
  void checkEmptyLineWhenNameGiven() {
    assertAll(() -> assertEquals(true, Validator.checkEmptyLine("-name mobile", false, false)));
  }

  @Test
  void validateInputWhenInvalid() {
    try {
      Validator.validateInput("");
      fail("No Exception thrown");
    } catch (Exception e) {
      assertEquals("Enter correct formatted input", e.getMessage(), "Wrong Exception Thrown");
    }
  }

  @Test
  void validateInputWhenValid() {
    String[] array = {"-name", "mobile"};
    assertAll(() -> assertArrayEquals(array, Validator.validateInput("-name mobile")));
  }

  @Test
  void addNameWhenNameAlreadyGiven() {
    try {
      Validator.addName(true, "mobile");
      fail("No Exception Thrown");
    } catch (Exception e) {
      assertEquals("Item name already given", e.getMessage(), "Wrong Exception Thrown");
    }
  }

  @Test
  void addNameWhenNameNotAlreadyGiven() {
    assertAll(() -> assertEquals("mobile", Validator.addName(false, "mobile")));
  }
}
