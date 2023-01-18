package org.taxcalculator.models;

import org.taxcalculator.constants.TaxConstants;
import org.taxcalculator.enums.ItemType;

/**
 * This class is used to represent an item.
 */
public class Item {
  private String itemName;
  private double itemPrice;
  private double itemTax;
  private int itemQuantity;
  private ItemType itemType;

  public Item() {

  }

  /**
   * This Constructor is used to create item.
   *
   * @param itemName     Name of the item
   * @param itemType     Type of the item
   * @param itemPrice    Price of the item
   * @param itemQuantity Quantity of the item
   */
  public Item(String itemName, String itemType, double itemPrice, int itemQuantity) {
    this.itemName = itemName;
    this.itemPrice = itemPrice;
    this.itemQuantity = itemQuantity;
    this.itemType = ItemType.valueOf(itemType);
    this.itemTax = this.calculateTax();
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

  private double calculateTax() {
    double tax = 0;
    switch (itemType) {
      case RAW:
        //12.5% on cost
        tax = TaxConstants.RAW_TYPE_TAX_PERCENTAGE * itemPrice;
        //converting into price to percentage
        tax /= 100.0;
        break;
      case MANUFACTURED:
        //12.5% on cost + 2% on tax+cost
        tax = TaxConstants.RAW_TYPE_TAX_PERCENTAGE * itemPrice;
        tax /= 100.0;
        tax += (TaxConstants.MANUFACTURED_TYPE_TAX_PERCENTAGE * (tax + itemPrice)) / 100;
        break;
      case IMPORTED:
        //10% import duty + tax 5rs if price under 100 after tax and import duty on cost or
        //+ 10rs if 100 to 200 else + 5%
        tax = TaxConstants.IMPORT_DUTY_PERCENTAGE * itemPrice;
        tax /= 100.0;
        if (tax + itemPrice <= TaxConstants.LIMIT_100) {
          tax += TaxConstants.SURCHARGE_TAX_COST_BELOW_100;
        } else if (tax + itemPrice <= TaxConstants.LIMIT_200) {
          tax += TaxConstants.SURCHARGE_TAX_COST_ABOVE_100_BELOW_200;
        } else {
          tax += (TaxConstants.SURCHARGE_PERCENTAGE * (itemPrice + tax)) / 100.0;
        }
        break;
      default:
        break;

    }
    return tax;
  }

  @Override
  public String toString() {
    return "Item name is " + itemName + "\nItem price is " + itemPrice
        + "\nItem tax is " + itemTax
        + "\nItem Cost is " + (itemPrice + itemTax) + "\n";
  }
}
