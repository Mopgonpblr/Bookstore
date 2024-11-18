package com.andersen.bookstore.view;

import com.andersen.bookstore.model.Bookstore;

import java.util.Scanner;

public class Menu {
    private final Bookstore bookstore;

    private final Scanner scanner = new Scanner(System.in);
    private int choice;

    public Menu(Bookstore bookstore) {
        this.bookstore = bookstore;
        showMainMenu();
    }

    public void showMainMenu() {
        System.out.println("""
                1. Open order
                2. List orders
                3. Change book availability
                4. exit
                """);
        choice = scanner.nextInt();

        switch (choice) {
            case 1 -> showOrderMenu();
            case 2 -> showOrdersList();
            case 3 -> showAvailabilityMenu();
        }
    }

    public void showOrderMenu() {
        bookstore.openOrder();
        bookstore.getCurrentOrder().getBooks().forEach(System.out::println);
        System.out.println("""
                1. Complete order
                2. Cancel order
                3. Continue
                """);
        choice = scanner.nextInt();

        switch (choice) {
            case 1 -> bookstore.completeOrder();
            case 2 -> bookstore.cancelOrder();
        }

        showMainMenu();
    }

    public void showOrdersList() {
        System.out.println();
        for (int i = 0; i < bookstore.getOrders().size() / 10 + 1; i++) {
            System.out.println("Page " + (i + 1));
            for (int j = 0; j < 10 && i * 10 + j < bookstore.getOrders().size(); j++) {
                System.out.println(bookstore.getOrders().get(i * 10 + j).toString());
            }
        }
        showMainMenu();
    }

    public void showAvailabilityMenu() {

        bookstore.getBooks().forEach(System.out::println);

        System.out.print("Enter the book number: ");
        int number = scanner.nextInt();

        System.out.println("Is book available? true/false");
        boolean choice = scanner.nextBoolean();

        bookstore.setBookAvailability(number, choice);

        showMainMenu();
    }
}
