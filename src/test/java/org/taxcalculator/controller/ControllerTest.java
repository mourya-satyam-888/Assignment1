package org.taxcalculator.controller;

import java.io.ByteArrayInputStream;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.taxcalculator.models.Item;
import org.taxcalculator.models.ItemCollection;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

  void compare(List<Item> First, List<Item> Second) {
    assertEquals(First.size(), Second.size(), "No of items added are different");
    for (int i = 0; i < First.size(); i++) {
      Item firstItem = First.get(i), secondItem = Second.get(i);
      assertEquals(firstItem.getItemName(), secondItem.getItemName(), "Name is different");
      assertEquals(firstItem.getItemQuantity(), secondItem.getItemQuantity(), "Quantity is different");
      assertEquals(firstItem.getItemPrice(), secondItem.getItemPrice(), "Price is different");
      assertEquals(firstItem.getItemTax(), secondItem.getItemTax(), "Taxed Cost is different");
      assertEquals(firstItem.getItemType().toString(), secondItem.getItemType().toString(), "Type is different");
    }
  }

  void addInput(StringBuilder input, String... cmds) {
    for (int i = 0; i < cmds.length - 1; i++) {
      input.append(cmds[i] + "\n");
    }
    input.append("\n" + cmds[cmds.length - 1] + "\n");
  }

  @Test
  void runWhenAllInputValid() {
    StringBuilder input = new StringBuilder();
    addInput(input, "-name item1", "-type raw", "y");
    addInput(input, "-name item2", "-type raw", "-quantity 5", "y");
    addInput(input, "-name item3", "-type raw", "-price 100", "n");
    System.setIn(new ByteArrayInputStream((input.toString()).getBytes()));
    ItemCollection itemCollection = new ItemCollection();
    itemCollection.addItem(Item.createItem("item1", "RAW", 0, 0));
    itemCollection.addItem(Item.createItem("item2", "RAW", 0, 5));
    itemCollection.addItem(Item.createItem("item3", "RAW", 100, 0));
    compare(Controller.run().getItemCollection(), itemCollection.getItemCollection());
  }

}