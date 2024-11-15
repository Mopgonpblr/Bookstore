package com.andersen.bookstore.view;

import com.andersen.bookstore.model.Book;
import com.andersen.bookstore.model.Bookstore;

public class WebView {
    private final Bookstore bookstore;

    public WebView(Bookstore bookstore) {
        this.bookstore = bookstore;
    }


    public String getOrderBookList() {
        bookstore.openOrder();
        StringBuilder bookList = new StringBuilder();
        for (Book book : bookstore.getCurrentOrder().getBooks()) {
            bookList.append("<br>").append(book);
        }

        return bookList.toString();
    }

    public String getOrdersList() {
        StringBuilder sb = new StringBuilder("\n");
        for (int i = 0; i < bookstore.getOrders().size() / 10 + 1; i++) {
            sb.append("Page ").append(i + 1).append("<br>");
            for (int j = 0; j < 10 && i * 10 + j < bookstore.getOrders().size(); j++) {
                sb.append(bookstore.getOrders().get(i * 10 + j).toString()).append("<br>");
            }
        }
        return sb.toString();
    }


    public Bookstore getBookstore() {
        return bookstore;
    }
}
