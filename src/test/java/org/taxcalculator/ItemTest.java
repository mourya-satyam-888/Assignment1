package org.taxcalculator;

import org.junit.jupiter.api.Test;
import org.taxcalculator.models.Item;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

  @Test
  void getFinalCost() {
    Item itemRaw = new Item("Mobile", "RAW", 100, 1);
    Item itemManufactured = new Item("Mobile", "MANUFACTURED", 100, 1);
    Item itemImported = new Item("Mobile", "IMPORTED", 100, 1);
    assertAll(() -> assertEquals(112.5, itemRaw.getFinalCost(), "RAW item tax calculation failed"),
        () -> assertEquals(114.75, itemManufactured.getFinalCost(), "Manufactured item tax calculation failed"),
        () -> assertEquals(120.0, itemImported.getFinalCost(), "Imported item tax calculation failed")
    );
  }
}