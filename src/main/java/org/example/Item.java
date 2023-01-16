package org.example;

public class Item {
    private static final double RAW_TYPE_TAX_PERCENTAGE = 12.5;
    private static final double MANUFACTURED_TYPE_TAX_PERCENTAGE = 2;
    private static final double IMPORT_DUTY_PERCENTAGE = 10;
    private static final double SURCHARGE_TAX_COST_BELOW_100 = 5;
    private static final double SURCHARGE_TAX_COST_ABOVE_100_BELOW_200 = 10;
    private static final double SURCHARGE_PERCENTAGE = 5;
    private static String itemName;
    private static double itemPrice, itemTax;
    private static int itemQuantity;
    private static ItemType itemType;

    public Item() {

    }

    public Item(String itemName, String itemType, double itemPrice, int itemQuantity) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemQuantity = itemQuantity;
        this.itemType = ItemType.valueOf(itemType);
        this.itemTax = this.calculateTax();
    }

    public static String getItemName() {
        return itemName;
    }

    public static void setItemName(String itemName) {
        Item.itemName = itemName;
    }

    public static double getItemPrice() {
        return itemPrice;
    }

    public static void setItemPrice(double itemPrice) {
        Item.itemPrice = itemPrice;
    }

    public static double getItemTax() {
        return itemTax;
    }

    public static void setItemTax(double itemTax) {
        Item.itemTax = itemTax;
    }

    public static int getItemQuantity() {
        return itemQuantity;
    }

    public static void setItemQuantity(int itemQuantity) {
        Item.itemQuantity = itemQuantity;
    }

    public static ItemType getItemType() {
        return itemType;
    }

    public static void setItemType(ItemType itemType) {
        Item.itemType = itemType;
    }

    public static double itemFinalCost() {
        return itemPrice + itemTax;
    }

    public static double calculateTax() {
        if (itemType == ItemType.RAW) {
            //12.5% on cost
            double tax = RAW_TYPE_TAX_PERCENTAGE * itemPrice;
            //converting into price to percentage
            tax /= 100.0;
            return tax;
        } else if (itemType == ItemType.MANUFACTURED) {
            //12.5% on cost + 2% on tax+cost
            double tax = RAW_TYPE_TAX_PERCENTAGE * itemPrice;
            tax /= 100.0;
            tax += (MANUFACTURED_TYPE_TAX_PERCENTAGE * (tax + itemPrice)) / 100;
            return tax;
        } else {
            //10% import duty + tax 5rs if price under 100 after tax and import duty on cost or
            //+ 10rs if 100 to 200 else + 5%
            double tax = IMPORT_DUTY_PERCENTAGE * itemPrice;
            tax /= 100.0;
            if (tax + itemPrice <= 100) {
                tax += SURCHARGE_TAX_COST_BELOW_100;
                return tax;
            } else if (tax + itemPrice <= 200) {
                tax += SURCHARGE_TAX_COST_ABOVE_100_BELOW_200;
                return tax;
            }
            tax += (SURCHARGE_PERCENTAGE * (itemPrice + tax)) / 100.0;
            return tax;
        }
    }
}
