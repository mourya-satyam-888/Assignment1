package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        do {
            addItem();
        } while (addAdditionalItem());
    }

    private static void addItem() {
        //todo
        Scanner scanner = new Scanner(System.in);
    }

    private static boolean addAdditionalItem() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to enter details of another item(y/n)?");
        while (true) {
            String userChoice = scanner.nextLine();
            if (userChoice.equals("y") || userChoice.equals("Y")) {
                return true;
            } else if (userChoice.equals("n") || userChoice.equals("N")) {
                return false;
            } else {
                System.out.println("Please enter valid choice (y/n)");
            }
        }
    }
}