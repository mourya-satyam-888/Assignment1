package org.taxCalculator;

import org.taxCalculator.util.Validator;

import java.util.Scanner;

public class Main {
    private static final String YES = "y";
    private static final String NO = "n";
    private static final String NAME_CMD = "-name";
    private static final String TYPE_CMD = "-type";
    private static final String PRICE_CMD = "-price";
    private static final String QUANTITY_CMD = "-quantity";
    private static Scanner scanner = new Scanner(System.in);

    private static boolean addAdditionalItem() {
        System.out.println("Do you want to enter details of another item(y/n)?");
        while (true) {
            String userChoice = scanner.nextLine();
            if (userChoice.equals(YES) || userChoice.equals(YES.toUpperCase())) {
                return true;
            } else if (userChoice.equals(NO) || userChoice.equals(NO.toUpperCase())) {
                return false;
            } else {
                System.out.println("Please enter valid choice (y/n)");
            }
        }
    }

    public static Boolean checkEmptyLine(String input, Boolean itemNameReceived, Boolean itemTypeReceived) throws Exception {
        Boolean emptyLine = Validator.checkEmptyLine(input);
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

    public static String[] validateInput(String input) throws Exception {
        String[] inputLine = input.split(" ", 2);
        Boolean validateInput = Validator.checkInputSize(inputLine);
        if (!validateInput) {
            throw new Exception("Enter correct formatted input");
        }
        return inputLine;
    }

    public static String addName(Boolean itemNameReceived, String itemName) throws Exception {
        if (itemNameReceived) {
            throw new Exception("Item name already given");
        }
        return itemName;
    }

    public static String addType(Boolean itemNameReceived, Boolean itemTypeReceived, String type) throws Exception {
        if (!itemNameReceived) {
            throw new Exception("Please enter item name first");
        }
        if (itemTypeReceived) {
            throw new Exception("Item type already received");
        }
        Boolean validateItemType = Validator.checkItemType(type);
        if (!validateItemType) {
            throw new Exception("Input valid type of item");
        }
        return type.toUpperCase();
    }

    public static double addPrice(Boolean itemNameReceived, Boolean itemPriceReceived, String price) throws Exception {
        if (!itemNameReceived) {
            throw new Exception("Please enter item name first");
        }
        if (itemPriceReceived) {
            throw new Exception("Item Price already received");
        }
        Boolean validateItemPrice = Validator.checkItemPrice(price);
        if (!validateItemPrice) {
            throw new Exception("Please enter valid price");
        }
        return Double.parseDouble(price);
    }

    public static int addQuantity(Boolean itemNameReceived, Boolean itemQuantityReceived, String quantity) throws Exception {
        if (!itemNameReceived) {
            throw new Exception("Please enter item name first");
        }
        if (itemQuantityReceived) {
            throw new Exception("Item quantity already received");
        }
        Boolean validateItemQuantity = Validator.checkItemQuantity(quantity);
        if (!validateItemQuantity) {
            throw new Exception("Please enter valid quantity");
        }
        return Integer.parseInt(quantity);
    }

    private static void addItem(ItemCollection itemCollection) {
        Boolean itemNameReceived = false, itemPriceReceived = false, itemTypeReceived = false, itemQuantityReceived = false;
        String itemName = "", itemType = "";
        double itemPrice = 0;
        int itemQuantity = 0;
        while (!itemNameReceived || !itemPriceReceived || !itemQuantityReceived || !itemTypeReceived) {
            String input = scanner.nextLine().strip();
            try {
                Boolean validLine = checkEmptyLine(input, itemNameReceived, itemTypeReceived);
                if (!validLine) {
                    break;
                }
                String[] inputLine = validateInput(input);
                switch (inputLine[0]) {
                    case NAME_CMD:
                        itemName = addName(itemNameReceived, inputLine[1]);
                        itemNameReceived = true;
                        break;
                    case TYPE_CMD:
                        itemType = addType(itemNameReceived, itemTypeReceived, inputLine[1]);
                        itemTypeReceived = true;
                        break;
                    case PRICE_CMD:
                        itemPrice = addPrice(itemNameReceived, itemPriceReceived, inputLine[1]);
                        itemPriceReceived = true;
                        break;
                    case QUANTITY_CMD:
                        itemQuantity = addQuantity(itemNameReceived, itemQuantityReceived, inputLine[1]);
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

    public static void main(String[] args) {
        ItemCollection itemCollection = new ItemCollection();
        do {
            addItem(itemCollection);
        } while (addAdditionalItem());
        itemCollection.printItems();
    }
}