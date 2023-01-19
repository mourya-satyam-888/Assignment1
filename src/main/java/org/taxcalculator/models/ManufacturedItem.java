package org.taxcalculator.models;

import org.taxcalculator.constants.TaxConstants;

/**
 * Class for ManufacturedItem Type.
 */
public class ManufacturedItem extends Item {
  public ManufacturedItem(String itemName, String itemType, double itemPrice, int itemQuantity) {
    super(itemName, itemType, itemPrice, itemQuantity);
  }

  protected double calculateTax() {
    double tax = 0;
    //12.5% on cost + 2% on tax+cost
    tax = TaxConstants.RAW_TYPE_TAX_PERCENTAGE * itemPrice;
    tax /= 100.0;
    tax += (TaxConstants.MANUFACTURED_TYPE_TAX_PERCENTAGE * (tax + itemPrice)) / 100;
    return tax;
  }
}
