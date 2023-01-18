package org.taxcalculator.constants;

/**
 * Its  includes all the Constants required for tax calculation.
 */
public class TaxConstants {
  public static final double RAW_TYPE_TAX_PERCENTAGE = 12.5;
  public static final double MANUFACTURED_TYPE_TAX_PERCENTAGE = 2;
  public static final double IMPORT_DUTY_PERCENTAGE = 10;
  public static final double SURCHARGE_TAX_COST_BELOW_100 = 5;
  public static final double SURCHARGE_TAX_COST_ABOVE_100_BELOW_200 = 10;
  public static final double SURCHARGE_PERCENTAGE = 5;
  public static final int LIMIT_100 = 100;
  public static final int LIMIT_200 = 200;

  private TaxConstants() {

  }
}
