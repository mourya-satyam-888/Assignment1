package org.taxcalculator.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {

  @Test
  void checkEmptyLineWhenInputEmpty() {
    assertEquals(true, Validator.validateEmptyLine(""));
  }

  @Test
  void checkEmptyLineWhenInputNonEmpty() {
    assertEquals(false, Validator.validateEmptyLine("something"));
  }

  @Test
  void checkInputSizeWhenOne() {
    assertEquals(false, Validator.checkInputSize(new String[1]));
  }

  @Test
  void checkInputSizeWhenTwo() {
    assertEquals(true, Validator.checkInputSize(new String[2]));
  }

  @Test
  void checkItemTypeWhenTypeValid() {
    assertEquals(true, Validator.checkItemType("raw"));
    assertEquals(true, Validator.checkItemType("RaW"));
  }

  @Test
  void checkItemTypeWhenTypeInvalid() {
    assertAll(() -> assertEquals(false, Validator.checkItemType("Nothing"), "Not a valid Type"),
        () -> assertEquals(false, Validator.checkItemType("1a"), "Alphanumeric given"));
  }

  @Test
  void checkItemPriceWhenValid() {
    assertEquals(true, Validator.checkItemPrice("100.5"));
  }

  @Test
  void checkItemPriceWhenInvalid() {
    assertAll(() -> assertEquals(false, Validator.checkItemPrice("-100"), "negative price accepted"),
        () -> assertEquals(false, Validator.checkItemPrice("1a"), "alphanumeric given"));
  }

  @Test
  void checkItemQuantityWhenValid() {
    assertEquals(true, Validator.checkItemQuantity("10"));
  }

  @Test
  void checkItemQuantityWhenInvalid() {
    assertAll(() -> assertEquals(false, Validator.checkItemQuantity("10.1"), "Quantity should be integer"),
        () -> assertEquals(false, Validator.checkItemQuantity("-100"), "Quantity can't be negative"));
  }
  
}