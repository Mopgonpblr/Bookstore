package com.andersen.bookstore.model;

import com.andersen.bookstore.controller.DataControl;
import com.andersen.bookstore.enums.Status;

import java.util.List;
import java.util.Random;

public class Bookstore {
    private final List<Book> books;
    private final List<Order> orders;
    private Order currentOrder;
    private final boolean availabilityLock;

    public Bookstore(List<Book> books, List<Order> orders, boolean availabilityLock) {
        this.books = books;
        this.orders = orders;
        this.availabilityLock = availabilityLock;
    }

    public void openOrder() {
        if (currentOrder == null) {
            currentOrder = new Order(orders.size() + 1);
            Random random = new Random();
            int bookNumber = 3;
            for (int i = 0; i < bookNumber; i++) {
                Book book;
                do {
                    book = books.get(random.nextInt(books.size()));
                } while (!book.getIsAvailable());
                currentOrder.addBook(book);
            }
            orders.add(currentOrder);
        }
    }

    public void cancelOrder() {
        if (currentOrder.getStatus() == Status.OPEN) {
            orders.remove(currentOrder);
            currentOrder = null;
        }
    }

    public void completeOrder() {
        currentOrder.closeOrder();
        currentOrder = null;
    }

    public void setBookAvailability(int number, boolean isAvailable) {
        if (!availabilityLock) {
            System.err.println("This feature is locked");
        } else {
            if (number > 0 && number <= books.size()) {
                books.get(number - 1).setIsAvailable(isAvailable);
                DataControl.updateBookAvailability(number, isAvailable);
            }
        }
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(Order currentOrder) {
        this.currentOrder = currentOrder;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public List<Book> getBooks() {
        return books;
    }
}
