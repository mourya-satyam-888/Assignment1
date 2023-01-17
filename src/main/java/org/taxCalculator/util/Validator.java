package org.taxCalculator.util;

import org.taxCalculator.ItemType;

public class Validator {
    private static final String EMPTY = "";

    public static Boolean checkEmptyLine(String input) {
        if (input.equals(EMPTY)) {
            return true;
        }
        return false;
    }

    public static Boolean checkInputSize(String[] inputLine) {
        if (inputLine.length < 2) {
            return false;
        }
        return true;
    }

    public static Boolean checkItemType(String inputType) {
        try {
            String type = inputType.toUpperCase();
            ItemType.valueOf(type);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

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
}
