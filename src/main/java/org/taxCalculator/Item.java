package org.taxCalculator;

public class Item {
    private static final double RAW_TYPE_TAX_PERCENTAGE = 12.5;
    private static final double MANUFACTURED_TYPE_TAX_PERCENTAGE = 2;
    private static final double IMPORT_DUTY_PERCENTAGE = 10;
    private static final double SURCHARGE_TAX_COST_BELOW_100 = 5;
    private static final double SURCHARGE_TAX_COST_ABOVE_100_BELOW_200 = 10;
    private static final double SURCHARGE_PERCENTAGE = 5;
    private static final int LIMIT_100 = 100;
    private static final int LIMIT_200 = 200;
    private String itemName;
    private double itemPrice, itemTax;
    private int itemQuantity;
    private ItemType itemType;

    public Item() {

    }

    public Item(String itemName, String itemType, double itemPrice, int itemQuantity) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemQuantity = itemQuantity;
        this.itemType = ItemType.valueOf(itemType);
        this.itemTax = this.calculateTax();
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public double getItemTax() {
        return itemTax;
    }

    public void setItemTax(double itemTax) {
        this.itemTax = itemTax;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public double getFinalCost() {
        return itemPrice + itemTax;
    }

    private double calculateTax() {
        double tax = 0;
        switch (itemType) {
            case RAW:
                //12.5% on cost
                tax = RAW_TYPE_TAX_PERCENTAGE * itemPrice;
                //converting into price to percentage
                tax /= 100.0;
                break;
            case MANUFACTURED:
                //12.5% on cost + 2% on tax+cost
                tax = RAW_TYPE_TAX_PERCENTAGE * itemPrice;
                tax /= 100.0;
                tax += (MANUFACTURED_TYPE_TAX_PERCENTAGE * (tax + itemPrice)) / 100;
                break;
            case IMPORTED:
                //10% import duty + tax 5rs if price under 100 after tax and import duty on cost or
                //+ 10rs if 100 to 200 else + 5%
                tax = IMPORT_DUTY_PERCENTAGE * itemPrice;
                tax /= 100.0;
                if (tax + itemPrice <= LIMIT_100) {
                    tax += SURCHARGE_TAX_COST_BELOW_100;
                } else if (tax + itemPrice <= LIMIT_200) {
                    tax += SURCHARGE_TAX_COST_ABOVE_100_BELOW_200;
                } else {
                    tax += (SURCHARGE_PERCENTAGE * (itemPrice + tax)) / 100.0;
                }
                break;
        }
        return tax;
    }

    @Override
    public String toString() {
        return "Item name is " + itemName + "\nItem price is " + itemPrice + "\nItem tax is " + itemTax +
                "\nItem Cost is " + (itemPrice + itemTax) + "\n";
    }
}
