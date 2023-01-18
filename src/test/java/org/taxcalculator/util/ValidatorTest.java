package org.taxcalculator.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {

  @Test
  void checkEmptyLine() {
    assertAll(() -> assertEquals(false, Validator.validateEmptyLine("something")),
        () -> assertEquals(true, Validator.validateEmptyLine("")));
  }

  @Test
  void checkInputSize() {
    assertAll(() -> assertEquals(false, Validator.checkInputSize(new String[1])),
        () -> assertEquals(true, Validator.checkInputSize(new String[2])));
  }

  @Test
  void checkItemType() {
    assertAll(() -> assertEquals(true, Validator.checkItemType("raw")),
        () -> assertEquals(true, Validator.checkItemType("RaW")),
        () -> assertEquals(false, Validator.checkItemType("Nothing"), "Not a valid Type"),
        () -> assertEquals(false, Validator.checkItemType("1a"), "Alphanumeric given"));
  }

  @Test
  void checkItemPrice() {
    assertAll(() -> assertEquals(true, Validator.checkItemPrice("100.5")),
        () -> assertEquals(false, Validator.checkItemPrice("-100"), "negative price accepted"),
        () -> assertEquals(false, Validator.checkItemPrice("1a"), "alphanumeric given"));
  }

  @Test
  void checkItemQuantity() {
    assertAll(() -> assertEquals(true, Validator.checkItemQuantity("10")),
        () -> assertEquals(false, Validator.checkItemQuantity("10.1"), "Quantity should be integer"),
        () -> assertEquals(false, Validator.checkItemQuantity("-100"), "Quantity can't be negative"));
  }
}