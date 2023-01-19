package org.taxcalculator;

import org.junit.jupiter.api.Test;
import org.taxcalculator.baseclasses.Item;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

  @Test
  void getFinalCost() {
    Item itemRaw = Item.createItem("Mobile", "RAW", 100, 1);
    Item itemManufactured = Item.createItem("Mobile", "MANUFACTURED", 100, 1);
    Item itemImported = Item.createItem("Mobile", "IMPORTED", 100, 1);
    assertAll(() -> assertEquals(112.5, itemRaw.getFinalCost(), "RAW item tax calculation failed"),
        () -> assertEquals(114.75, itemManufactured.getFinalCost(), "Manufactured item tax calculation failed"),
        () -> assertEquals(120.0, itemImported.getFinalCost(), "Imported item tax calculation failed")
    );
  }
}