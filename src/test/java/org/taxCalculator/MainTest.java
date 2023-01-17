package org.taxCalculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void checkEmptyLine() {
        try {
            Main.checkEmptyLine("", true, false);
            fail("No Exception Thrown expected Enter item type Exception");
        } catch (Exception e) {
            assertEquals("Enter item type", e.getMessage(), "Wrong Exception Thrown");
        }
        assertAll(() -> assertEquals(false, Main.checkEmptyLine("", true, true)),
                () -> assertEquals(true, Main.checkEmptyLine("-name mobile", false, false)));
    }

    @Test
    void validateInput() {
        try {
            Main.validateInput("");
            fail("No Exception thrown");
        } catch (Exception e) {
            assertEquals("Enter correct formatted input", e.getMessage(), "Wrong Exception Thrown");
        }
        String[] array = {"-name", "mobile"};
        assertAll(() -> assertArrayEquals(array, Main.validateInput("-name mobile")));
    }

    @Test
    void addName() {
        try {
            Main.addName(true, "mobile");
            fail("No Exception Thrown");
        } catch (Exception e) {
            assertEquals("Item name already given", e.getMessage(), "Wrong Exception Thrown");
        }
        assertAll(() -> assertEquals("mobile", "mobile"));
    }

    @Test
    void addType() {
        try {
            Main.addType(false, false, "raw");
            fail("No Exception Thrown");
        } catch (Exception e) {
            assertEquals("Please enter item name first", e.getMessage(), "Wrong Exception Thrown");
        }
        try {
            Main.addType(true, true, "raw");
            fail("No Exception Thrown");
        } catch (Exception e) {
            assertEquals("Item type already received", e.getMessage(), "Wrong Exception Thrown");
        }
        try {
            Main.addType(true, false, "r");
            fail("No Exception Thrown");
        } catch (Exception e) {
            assertEquals("Input valid type of item", e.getMessage(), "Wrong Exception Thrown");
        }
        assertAll(() -> assertEquals("RAW", Main.addType(true, false, "raw")));
    }

    @Test
    void addPrice() {
        try {
            Main.addPrice(false, false, "100");
            fail("No Exception Thrown");
        } catch (Exception e) {
            assertEquals("Please enter item name first", e.getMessage(), "Wrong Exception Thrown");
        }
        try {
            Main.addPrice(true, true, "100");
            fail("No Exception Thrown");
        } catch (Exception e) {
            assertEquals("Item Price already received", e.getMessage(), "Wrong Exception Thrown");
        }
        try {
            Main.addPrice(true, false, "-100");
            fail("No Exception Thrown");
        } catch (Exception e) {
            assertEquals("Please enter valid price", e.getMessage(), "Wrong Exception Thrown");
        }
        assertAll(() -> assertEquals(100.0, Main.addPrice(true, false, "100")));
    }

    @Test
    void addQuantity() {
        try {
            Main.addQuantity(false, false, "100");
            fail("No Exception Thrown");
        } catch (Exception e) {
            assertEquals("Please enter item name first", e.getMessage(), "Wrong Exception Thrown");
        }
        try {
            Main.addQuantity(true, true, "100");
            fail("No Exception Thrown");
        } catch (Exception e) {
            assertEquals("Item quantity already received", e.getMessage(), "Wrong Exception Thrown");
        }
        try {
            Main.addQuantity(true, false, "-100");
            fail("No Exception Thrown");
        } catch (Exception e) {
            assertEquals("Please enter valid quantity", e.getMessage(), "Wrong Exception Thrown");
        }
        assertAll(() -> assertEquals(100, Main.addQuantity(true, false, "100")));
    }
}