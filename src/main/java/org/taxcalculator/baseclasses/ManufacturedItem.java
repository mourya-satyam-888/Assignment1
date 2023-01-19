package org.taxcalculator.baseclasses;

import org.taxcalculator.constants.TaxConstants;

/**
 * Class for ManufacturedItem Type.
 */
public class ManufacturedItem extends Item {
  /**
   * Instantiates a new Manufactured item.
   *
   * @param itemName     the item name
   * @param itemType     the item type
   * @param itemPrice    the item price
   * @param itemQuantity the item quantity
   */
  public ManufacturedItem(final String itemName, final String itemType,
                          final double itemPrice, final int itemQuantity) {
    super(itemName, itemType, itemPrice, itemQuantity);
  }

  /**
   * Used to calculate tax to manufactured items.
   *
   * @return return tax
   */
  protected double calculateTax() {
    double tax;
    //12.5% on cost + 2% on tax+cost
    tax = TaxConstants.RAW_TYPE_TAX_PERCENTAGE * itemPrice;
    tax /= 100.0;
    tax += (TaxConstants.MANUFACTURED_TYPE_TAX_PERCENTAGE * (tax + itemPrice)) / 100;
    return tax;
  }
}
