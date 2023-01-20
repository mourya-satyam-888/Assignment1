package org.taxcalculator.baseclasses;

import org.taxcalculator.enums.ItemType;

/**
 * This class is used to represent an item.
 */
public abstract class Item {
  /**
   * The Item name.
   */
  protected String itemName;
  /**
   * The Item price.
   */
  protected double itemPrice;
  /**
   * The Item tax.
   */
  protected double itemTax;
  /**
   * The Item quantity.
   */
  protected int itemQuantity;
  /**
   * The Item type.
   */
  protected ItemType itemType;

  /**
   * Calculate tax double.
   *
   * @return the double
   */
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
  protected Item(final String itemName, final String itemType,
                 final double itemPrice, final int itemQuantity) {
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
  public static Item createItem(final String itemName, final String itemType,
                                final double itemPrice, final int itemQuantity) {
    final ItemType type = ItemType.valueOf(itemType);
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

  public void setItemName(final String itemName) {
    this.itemName = itemName;
  }

  public double getItemPrice() {
    return itemPrice;
  }

  public void setItemPrice(final double itemPrice) {
    this.itemPrice = itemPrice;
  }

  public double getItemTax() {
    return itemTax;
  }

  public void setItemTax(final double itemTax) {
    this.itemTax = itemTax;
  }

  public int getItemQuantity() {
    return itemQuantity;
  }

  public void setItemQuantity(final int itemQuantity) {
    this.itemQuantity = itemQuantity;
  }

  public ItemType getItemType() {
    return itemType;
  }

  public void setItemType(final ItemType itemType) {
    this.itemType = itemType;
  }

  /**
   * Gets final cost.
   *
   * @return the final cost
   */
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
