package com.andersen.bookstore.controller;

import com.andersen.bookstore.enums.Status;
import com.andersen.bookstore.model.Book;
import com.andersen.bookstore.model.Bookstore;
import com.andersen.bookstore.view.WebView;

import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class Controller {

    private final DataControl dataControl;
    private WebView webView;
    private String filepath;

    public Controller(){
        dataControl = new DataControl();
    }

    public void run() {
        Properties properties = dataControl.readProperties();

        filepath = properties.getProperty("filepath");
        boolean availabilityLock = Boolean.parseBoolean(properties.getProperty("availabilityLock"));

        Bookstore bookstore = new Bookstore(createLibrary(), dataControl.loadLastState(filepath), availabilityLock);
        if (!bookstore.getOrders().isEmpty() && bookstore.getOrders().getLast().getStatus() == Status.OPEN) {
            bookstore.setCurrentOrder(bookstore.getOrders().getLast());
        }

        webView = new WebView(bookstore);
    }

    private List<Book> createLibrary() {
        List<Book> books = new LinkedList<>();
        books.add(new Book("Pride and Prejudice", "Jane Austin", 9.99, true));
        books.add(new Book("The Catcher in the Rye", "J.D. Salinger", 5.99, true));
        books.add(new Book("Animal Farm", "George Orwell", 7.99, true));
        books.add(new Book("Pan Tadeusz", "Adam Mickiewicz", 9.99, true));
        books.add(new Book("Eugene Onegin", "Alexander Pushkin", 0.49, true));
        return books;
    }

    public WebView getWebView() {
        return webView;
    }

    public DataControl getDataControl(){
        return dataControl;
    }

    public String getFilepath(){
        return filepath;
    }
}
