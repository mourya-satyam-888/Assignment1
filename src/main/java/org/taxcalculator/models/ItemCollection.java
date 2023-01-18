package org.taxcalculator.models;

import java.util.ArrayList;
import java.util.List;

/**
 * ItemCollection is the collection of items.
 */
public class ItemCollection {
  private List<Item> itemCollection = new ArrayList<>();

  public void addItem(Item item) {
    itemCollection.add(item);
  }

  public List<Item> getItemCollection() {
    return itemCollection;
  }

  /**
   * This method is used to print all the Items in Collection.
   */
  public void printItems() {
    for (Item item : itemCollection) {
      System.out.println(item);
    }
  }
}
