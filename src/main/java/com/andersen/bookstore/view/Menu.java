package com.andersen.bookstore.view;

import com.andersen.bookstore.model.Bookstore;

import java.util.Scanner;

public class Menu {
    private final Bookstore bookstore;

    private final Scanner scanner = new Scanner(System.in);
    private int choice;

    public Menu(Bookstore bookstore) {
        this.bookstore = bookstore;
    }


    public void showMainMenu() {
        System.out.println();
        System.out.println("1. Open order");
        System.out.println("2. List orders");
        System.out.println("3. exit");

        choice = scanner.nextInt();

        if (choice == 1) {
            showOrderMenu();
        } else if (choice == 2) {
            showOrdersList();
        }
    }

    public void showOrderMenu() {
        bookstore.openOrder();
        for (int i = 0; i < bookstore.getCurrentOrder().getBooks().size(); i++) {
            System.out.println(bookstore.getCurrentOrder().getBooks().get(i));
        }
        System.out.println();
        System.out.println("1. Complete order");
        System.out.println("2. Cancel order");
        System.out.println("3. Continue");
        choice = scanner.nextInt();

        if (choice == 1) {
            bookstore.completeOrder();
        } else if (choice == 2){
            bookstore.cancelOrder();
        }

        showMainMenu();
    }

    public void showOrdersList() {
        System.out.println();
        for (int i = 0; i < bookstore.getOrders().size() / 10 + 1; i++) {
            System.out.println("Page " + (i + 1));
            for (int j = 0; j < 10 && i * 10 + j < bookstore.getOrders().size(); j++) {
                System.out.println(bookstore.getOrders().get(i * 10 + j));
            }
        }
        showMainMenu();
    }
}
