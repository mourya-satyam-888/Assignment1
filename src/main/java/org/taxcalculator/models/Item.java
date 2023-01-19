package org.taxcalculator.models;

import org.taxcalculator.enums.ItemType;

/**
 * This class is used to represent an item.
 */
public abstract class Item {
  protected String itemName;
  protected double itemPrice;
  protected double itemTax;
  protected int itemQuantity;
  protected ItemType itemType;

  protected abstract double calculateTax();

  private Item() {

  }

  /**
   * This Constructor is used to create item.
   *
   * @param itemName     Name of the item
   * @param itemType     Type of the item
   * @param itemPrice    Price of the item
   * @param itemQuantity Quantity of the item
   */
  protected Item(String itemName, String itemType, double itemPrice, int itemQuantity) {
    this.itemName = itemName;
    this.itemPrice = itemPrice;
    this.itemQuantity = itemQuantity;
    this.itemType = ItemType.valueOf(itemType);
    this.itemTax = calculateTax();
  }

  /**
   * This method is used to create item according to Type.
   *
   * @param itemName     item name
   * @param itemType     item type
   * @param itemPrice    item price
   * @param itemQuantity item quantity
   * @return return instance of subclass according to class
   */
  public static Item createItem(String itemName, String itemType,
                                double itemPrice, int itemQuantity) {
    ItemType type = ItemType.valueOf(itemType);
    switch (type) {
      case RAW:
        return new RawItem(itemName, itemType, itemPrice, itemQuantity);
      case MANUFACTURED:
        return new ManufacturedItem(itemName, itemType, itemPrice, itemQuantity);
      case IMPORTED:
        return new ImportedItem(itemName, itemType, itemPrice, itemQuantity);
      default:
        break;
    }
    return null;
  }

  public String getItemName() {
    return itemName;
  }

  public void setItemName(String itemName) {
    this.itemName = itemName;
  }

  public double getItemPrice() {
    return itemPrice;
  }

  public void setItemPrice(double itemPrice) {
    this.itemPrice = itemPrice;
  }

  public double getItemTax() {
    return itemTax;
  }

  public void setItemTax(double itemTax) {
    this.itemTax = itemTax;
  }

  public int getItemQuantity() {
    return itemQuantity;
  }

  public void setItemQuantity(int itemQuantity) {
    this.itemQuantity = itemQuantity;
  }

  public ItemType getItemType() {
    return itemType;
  }

  public void setItemType(ItemType itemType) {
    this.itemType = itemType;
  }

  public double getFinalCost() {
    return itemPrice + itemTax;
  }

  @Override
  public String toString() {
    return "Item name is " + itemName + "\nItem price is " + itemPrice
        + "\nItem tax is " + itemTax
        + "\nItem Cost is " + (itemPrice + itemTax) + "\n";
  }
}
