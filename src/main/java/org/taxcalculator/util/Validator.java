package org.taxcalculator.util;

import org.taxcalculator.constants.Commands;
import org.taxcalculator.enums.ItemType;

/**
 * This includes all the validations required for inputs to be checked.
 */
public class Validator {

  /**
   * This method check whether line is empty or not.
   *
   * @param input Given input to be checked
   * @return It returns true in case line is empty else false
   */
  public static Boolean validateEmptyLine(String input) {
    if (input.equals(Commands.EMPTY)) {
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
  public static Boolean checkInputSize(String[] inputLine) {
    if (inputLine.length < 2) {
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
  public static Boolean checkItemType(String inputType) {
    try {
      String type = inputType.toUpperCase();
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
  public static Boolean checkItemPrice(String inputPrice) {
    try {
      double price = Double.parseDouble(inputPrice);
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
  public static Boolean checkItemQuantity(String inputQuantity) {
    try {
      int quantity = Integer.parseInt(inputQuantity);
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
  public static Boolean checkEmptyLine(String input, Boolean itemNameReceived,
                                       Boolean itemTypeReceived) throws Exception {
    Boolean emptyLine = validateEmptyLine(input);
    if (emptyLine) {
      if (itemNameReceived) {
        if (itemTypeReceived) {
          return false;
        } else {
          throw new Exception("Enter item type");
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
  public static String[] validateInput(String input) throws Exception {
    String[] inputLine = input.split(" ", 2);
    Boolean validateInput = checkInputSize(inputLine);
    if (!validateInput) {
      throw new Exception("Enter correct formatted input");
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
  public static String addName(Boolean itemNameReceived, String itemName) throws Exception {
    if (itemNameReceived) {
      throw new Exception("Item name already given");
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
  public static String addType(Boolean itemNameReceived, Boolean itemTypeReceived,
                               String type) throws Exception {
    if (!itemNameReceived) {
      throw new Exception("Please enter item name first");
    }
    if (itemTypeReceived) {
      throw new Exception("Item type already received");
    }
    Boolean validateItemType = checkItemType(type);
    if (!validateItemType) {
      throw new Exception("Input valid type of item");
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
  public static double addPrice(Boolean itemNameReceived, Boolean itemPriceReceived,
                                String price) throws Exception {
    if (!itemNameReceived) {
      throw new Exception("Please enter item name first");
    }
    if (itemPriceReceived) {
      throw new Exception("Item Price already received");
    }
    Boolean validateItemPrice = checkItemPrice(price);
    if (!validateItemPrice) {
      throw new Exception("Please enter valid price");
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
  public static int addQuantity(Boolean itemNameReceived, Boolean itemQuantityReceived,
                                String quantity) throws Exception {
    if (!itemNameReceived) {
      throw new Exception("Please enter item name first");
    }
    if (itemQuantityReceived) {
      throw new Exception("Item quantity already received");
    }
    Boolean validateItemQuantity = checkItemQuantity(quantity);
    if (!validateItemQuantity) {
      throw new Exception("Please enter valid quantity");
    }
    return Integer.parseInt(quantity);
  }

  private Validator() {

  }
}
