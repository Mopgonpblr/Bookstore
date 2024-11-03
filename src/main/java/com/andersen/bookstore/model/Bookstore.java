package com.andersen.bookstore.model;

import com.andersen.bookstore.enums.Status;

import java.util.List;
import java.util.Random;

public class Bookstore {
    private final List<Book> books;
    private final List<Order> orders;
    private Order currentOrder;

    public Bookstore(List<Book> books, List<Order> orders) {
        this.books = books;
        this.orders = orders;
    }

    public void openOrder() {
        if (currentOrder == null) {
            currentOrder = new Order(orders.size() + 1);
            Random random = new Random();
            for (int i = 0; i < 3; i++) {
                currentOrder.addBook(books.get(random.nextInt(books.size())));
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

    public Order getCurrentOrder(){
        return currentOrder;
    }

    public List <Order> getOrders(){
        return orders;
    }

 /*   public void listOrders() {
        Scanner scanner = new Scanner(System.in);
        int i = 0;
        int j = 10;
        int page = 1;
        while (page != 0) {
            System.out.println("Page " + page);
            for (; i < j; i++) {
                System.out.println(orders.get(i));
            }
            page = scanner.nextInt();
            if (page == 2) {
                i += 10;
                j += 10;
            } else if (page == 1) {
                i -= 10;
                j -= 10;
            }
        }
    }*/
}
