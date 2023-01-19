package org.taxcalculator.models;

import org.taxcalculator.constants.TaxConstants;

/**
 * Class for ImportedItem Type.
 */
public class ImportedItem extends Item {
  public ImportedItem(String itemName, String itemType, double itemPrice, int itemQuantity) {
    super(itemName, itemType, itemPrice, itemQuantity);
  }

  protected double calculateTax() {
    double tax = 0;
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
    return tax;
  }
}
