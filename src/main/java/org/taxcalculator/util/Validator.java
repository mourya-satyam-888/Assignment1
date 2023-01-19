package org.taxcalculator.util;

import org.taxcalculator.constants.CommandConstants;
import org.taxcalculator.constants.TaxConstants;
import org.taxcalculator.enums.ItemType;
import org.taxcalculator.exceptions.ValidationException;

/**
 * This includes all the validations required for inputs to be checked.
 */
public final class Validator {

  /**
   * This method check whether line is empty or not.
   *
   * @param input Given input to be checked
   * @return It returns true in case line is empty else false
   */
  public static Boolean validateEmptyLine(final String input) {
    if (CommandConstants.EMPTY.equals(input)) {
      return true;
    }
    return false;
  }

  /**
   * This method checks whether sufficient parameter is given or not.
   *
   * @param inputLine it contains the input line
   * @return returns true if line is valid else false
   */
  public static Boolean checkInputSize(final String... inputLine) {
    if (inputLine.length < TaxConstants.SIZE_LIMIT) {
      return false;
    }
    return true;
  }

  /**
   * This item checks for type of item.
   *
   * @param inputType it gives type of item
   * @return returns true if the type is valid else false
   */
  public static Boolean checkItemType(final String inputType) {
    try {
      final String type = inputType.toUpperCase();
      ItemType.valueOf(type);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * It checks for item price.
   *
   * @param inputPrice It is the item price given
   * @return returns true if price is valid else false
   */
  public static Boolean checkItemPrice(final String inputPrice) {
    try {
      final double price = Double.parseDouble(inputPrice);
      if (price < 0) {
        return false;
      }
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * It checks item quantity.
   *
   * @param inputQuantity It is the given quantity of item
   * @return returns true if quantity is valid else false
   */
  public static Boolean checkItemQuantity(final String inputQuantity) {
    try {
      final int quantity = Integer.parseInt(inputQuantity);
      if (quantity < 0) {
        return false;
      }
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * checkEmptyLine function is used to decide what to do in case of empty line.
   *
   * @param input            input line by user
   * @param itemNameReceived Represent whether item name received or not
   * @param itemTypeReceived Represent whether item type received or not
   * @return returns true if we should proceed further with input
   * @throws Exception item name may not be given
   */
  public static Boolean checkEmptyLine(final String input, final Boolean itemNameReceived,
                                       final Boolean itemTypeReceived) {
    final Boolean emptyLine = validateEmptyLine(input);
    if (emptyLine) {
      if (itemNameReceived) {
        if (itemTypeReceived) {
          return false;
        } else {
          throw new ValidationException("Enter item type");
        }
      } else {
        return false;
      }
    }
    return true;
  }

  /**
   * This method validates the given input.
   *
   * @param input given input by user
   * @return return array of String of command and value
   * @throws Exception Improper formatting
   */
  public static String[] validateInput(final String input) {
    final String[] inputLine = input.split(" ", 2);
    final Boolean validateInput = checkInputSize(inputLine);
    if (!validateInput) {
      throw new ValidationException("Enter correct formatted input");
    }
    return inputLine;
  }

  /**
   * This method checks for the required condition for name to be accepted.
   *
   * @param itemNameReceived it represents item name received or not
   * @param itemName         it represents name of item
   * @return return name of item if valid
   * @throws Exception throws exception item name already exists
   */
  public static String addName(final Boolean itemNameReceived, final String itemName) {
    if (itemNameReceived) {
      throw new ValidationException("Item name already given");
    }
    return itemName;
  }

  /**
   * This method checks for the required condition for type to be accepted.
   *
   * @param itemNameReceived it represents item name received or not
   * @param itemTypeReceived it represents item type received or not
   * @param type             it represents type of item
   * @return return type of item if valid
   * @throws Exception throws exception item type already exist,item name not given,wrong formatting
   */
  public static String addType(final Boolean itemNameReceived, final Boolean itemTypeReceived,
                               final String type) {
    if (!itemNameReceived) {
      throw new ValidationException("Please enter item name first");
    }
    if (itemTypeReceived) {
      throw new ValidationException("Item type already received");
    }
    final Boolean validateItemType = checkItemType(type);
    if (!validateItemType) {
      throw new ValidationException("Input valid type of item");
    }
    return type.toUpperCase();
  }

  /**
   * This method checks for the required condition for price to be accepted.
   *
   * @param itemNameReceived  it represents item name received or not
   * @param itemPriceReceived it represents item price received or not
   * @param price             it represents price of item
   * @return return price of item if valid
   * @throws Exception throws exception item price already exist,
   *                   item name not given,wrong formatting
   */
  public static double addPrice(final Boolean itemNameReceived, final Boolean itemPriceReceived,
                                final String price) {
    if (!itemNameReceived) {
      throw new ValidationException("Please enter item name first");
    }
    if (itemPriceReceived) {
      throw new ValidationException("Item Price already received");
    }
    final Boolean validateItemPrice = checkItemPrice(price);
    if (!validateItemPrice) {
      throw new ValidationException("Please enter valid price");
    }
    return Double.parseDouble(price);
  }

  /**
   * This method checks for the required condition for quantity to be accepted.
   *
   * @param itemNameReceived     it represents item name received or not
   * @param itemQuantityReceived it represents item quantity received or not
   * @param quantity             it represents quantity of item
   * @return return quantity of item if valid
   * @throws Exception throws exception item quantity already exist,
   *                   item name not given,wrong formatting
   */
  public static int addQuantity(final Boolean itemNameReceived, final Boolean itemQuantityReceived,
                                final String quantity) {
    if (!itemNameReceived) {
      throw new ValidationException("Please enter item name first");
    }
    if (itemQuantityReceived) {
      throw new ValidationException("Item quantity already received");
    }
    final Boolean validateItemQuantity = checkItemQuantity(quantity);
    if (!validateItemQuantity) {
      throw new ValidationException("Please enter valid quantity");
    }
    return Integer.parseInt(quantity);
  }

  private Validator() {

  }
}
