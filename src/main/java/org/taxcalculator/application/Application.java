package org.taxcalculator.application;

import java.util.HashMap;
import java.util.Scanner;
import org.taxcalculator.baseclasses.Item;
import org.taxcalculator.baseclasses.ItemCollection;
import org.taxcalculator.constants.CommandConstants;
import org.taxcalculator.exceptions.ValidationException;
import org.taxcalculator.util.Validator;

/**
 * The Controller class is used to control whole project.
 */
public final class Application {
  /**
   * Scanner object to process input.
   */
  private static Scanner scanner = new Scanner(System.in);

  private Application() {

  }

  /**
   * This method is used to ask user for addition of extra items.
   *
   * @return returns true if use wants to enter details of more items
   */
  public static boolean addAdditionalItem() {
    System.out.println("Do you want to enter details of another item(y/n)?");
    while (true) {
      final String userChoice = scanner.nextLine();
      if (CommandConstants.YES.equalsIgnoreCase(userChoice)) {
        return true;
      } else if (CommandConstants.NO.equalsIgnoreCase(userChoice)) {
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
  public static void addItem(final ItemCollection itemCollection) {
    final HashMap<String, Boolean> received = new HashMap<>();
    String itemName = "";
    String itemType = "";
    double itemPrice = 0;
    int itemQuantity = 0;
    while (received.size() < 4) {
      final String input = scanner.nextLine().strip();
      try {
        final Boolean validLine;
        validLine = Validator.checkEmptyLine(input,
            received.getOrDefault(CommandConstants.NAME_CMD, false),
            received.getOrDefault(CommandConstants.TYPE_CMD, false));
        if (!validLine) {
          break;
        }
        final String[] inputLine = Validator.validateInput(input);
        final String command = inputLine[0];
        final String value = inputLine[1];
        switch (command) {
          case CommandConstants.NAME_CMD:
            itemName = Validator.addName(
                received.getOrDefault(CommandConstants.NAME_CMD, false),
                value);
            received.put(CommandConstants.NAME_CMD, true);
            break;
          case CommandConstants.TYPE_CMD:
            itemType = Validator.addType(
                received.getOrDefault(CommandConstants.NAME_CMD, false),
                received.getOrDefault(CommandConstants.TYPE_CMD, false), value);
            received.put(CommandConstants.TYPE_CMD, true);
            break;
          case CommandConstants.PRICE_CMD:
            itemPrice = Validator.addPrice(
                received.getOrDefault(CommandConstants.NAME_CMD, false),
                received.getOrDefault(CommandConstants.PRICE_CMD, false), value);
            received.put(CommandConstants.PRICE_CMD, true);
            break;
          case CommandConstants.QUANTITY_CMD:
            itemQuantity = Validator.addQuantity(
                received.getOrDefault(CommandConstants.NAME_CMD, false),
                received.getOrDefault(CommandConstants.QUANTITY_CMD, false), value);
            received.put(CommandConstants.QUANTITY_CMD, true);
            break;
          default:
            throw new ValidationException("Please enter valid command");
        }
      } catch (ValidationException e) {
        System.out.println(e.getMessage());
      }
    }
    if (received.getOrDefault(CommandConstants.NAME_CMD, false)
        && received.getOrDefault(CommandConstants.TYPE_CMD, false)) {
      final Item item = Item.createItem(itemName, itemType, itemPrice, itemQuantity);
      itemCollection.addItem(item);
    }
  }

  private static void displayItems(final ItemCollection itemCollection) {
    System.out.println("The Following Data is Given: ");
    System.out.println(itemCollection.getItemCollection());
  }

  /**
   * This method is used to run main logic of application.
   */
  public static ItemCollection run() {
    final ItemCollection itemCollection = new ItemCollection();
    do {
      addItem(itemCollection);
    } while (addAdditionalItem());
    displayItems(itemCollection);
    return itemCollection;
  }

}
