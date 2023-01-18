package org.taxcalculator.controller;

import java.util.Scanner;
import org.taxcalculator.constants.Commands;
import org.taxcalculator.models.Item;
import org.taxcalculator.models.ItemCollection;
import org.taxcalculator.util.Validator;

/**
 * The Controller class is used to control whole project.
 */
public class Controller {
  private static Scanner scanner = new Scanner(System.in);

  /**
   * This method is used to ask user for addition of extra items.
   *
   * @return returns true if use wants to enter details of more items
   */
  public static boolean addAdditionalItem() {
    System.out.println("Do you want to enter details of another item(y/n)?");
    while (true) {
      String userChoice = scanner.nextLine();
      if (userChoice.equals(Commands.YES) || userChoice.equals(Commands.YES.toUpperCase())) {
        return true;
      } else if (userChoice.equals(Commands.NO) || userChoice.equals(Commands.NO.toUpperCase())) {
        return false;
      } else {
        System.out.println("Please enter valid choice (y/n)");
      }
    }
  }

  /**
   * This method is used to add items into the collection.
   *
   * @param itemCollection requires in which item to be added
   */
  public static void addItem(ItemCollection itemCollection) {
    Boolean itemNameReceived = false;
    Boolean itemPriceReceived = false;
    Boolean itemTypeReceived = false;
    Boolean itemQuantityReceived = false;
    String itemName = "";
    String itemType = "";
    double itemPrice = 0;
    int itemQuantity = 0;
    while (!itemNameReceived || !itemPriceReceived || !itemQuantityReceived || !itemTypeReceived) {
      String input = scanner.nextLine().strip();
      try {
        Boolean validLine = Validator.checkEmptyLine(input, itemNameReceived, itemTypeReceived);
        if (!validLine) {
          break;
        }
        String[] inputLine = Validator.validateInput(input);
        String command = inputLine[0];
        String value = inputLine[1];
        switch (command) {
          case Commands.NAME_CMD:
            itemName = Validator.addName(itemNameReceived, value);
            itemNameReceived = true;
            break;
          case Commands.TYPE_CMD:
            itemType = Validator.addType(itemNameReceived, itemTypeReceived, value);
            itemTypeReceived = true;
            break;
          case Commands.PRICE_CMD:
            itemPrice = Validator.addPrice(itemNameReceived, itemPriceReceived, value);
            itemPriceReceived = true;
            break;
          case Commands.QUANTITY_CMD:
            itemQuantity = Validator.addQuantity(itemNameReceived, itemQuantityReceived, value);
            itemQuantityReceived = true;
            break;
          default:
            throw new Exception("Please enter valid command");
        }
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }
    if (itemNameReceived && itemTypeReceived) {
      Item item = new Item(itemName, itemType, itemPrice, itemQuantity);
      itemCollection.addItem(item);
    }
  }

  private static void displayItems(ItemCollection itemCollection) {
    System.out.println("The Following Data is Given");
    System.out.println(itemCollection.getItemCollection());
  }

  /**
   * This method is used to run main logic of application.
   */
  public static ItemCollection run() {
    ItemCollection itemCollection = new ItemCollection();
    do {
      addItem(itemCollection);
    } while (addAdditionalItem());
    displayItems(itemCollection);
    return itemCollection;
  }

}
