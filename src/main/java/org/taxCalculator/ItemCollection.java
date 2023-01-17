package org.taxCalculator;

import java.util.ArrayList;
import java.util.List;

public class ItemCollection {
    private List<Item> itemCollection = new ArrayList<>();

    public void addItem(Item item) {
        itemCollection.add(item);
    }

    public List<Item> getItemCollection() {
        return itemCollection;
    }

    public void printItems() {
        for (Item item : itemCollection) {
            System.out.println(item);
        }
    }
}
