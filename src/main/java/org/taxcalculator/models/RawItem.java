package org.taxcalculator.models;

import org.taxcalculator.constants.TaxConstants;

/**
 * Class for RawItem Type.
 */
public class RawItem extends Item {
  public RawItem(String itemName, String itemType, double itemPrice, int itemQuantity) {
    super(itemName, itemType, itemPrice, itemQuantity);
  }

  protected double calculateTax() {
    double tax = 0;
    //12.5% on cost
    tax = TaxConstants.RAW_TYPE_TAX_PERCENTAGE * itemPrice;
    //converting into price to percentage
    tax /= 100.0;
    return tax;
  }
}
