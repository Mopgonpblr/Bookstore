package com.andersen.bookstore.controller;

import com.andersen.bookstore.enums.Status;
import com.andersen.bookstore.model.Book;
import com.andersen.bookstore.model.Bookstore;
import com.andersen.bookstore.view.Menu;
import com.andersen.bookstore.view.WebView;

import java.util.List;
import java.util.Properties;

public class Controller {

    private final DataControl dataControl;
    private WebView webView;
    private String filepath;
    private Bookstore bookstore;
    private static Properties properties;

    public Controller() {
        dataControl = new DataControl();
    }

    public void run(String type) {
        properties = dataControl.readProperties();

        if (type.equals("web")) {
            filepath = properties.getProperty("filepath");
        } else {
            filepath = properties.getProperty("filepath2");
        }
        boolean availabilityLock = Boolean.parseBoolean(properties.getProperty("availabilityLock"));

        List<Book> library = dataControl.fetchBooks();

        bookstore = new Bookstore(library, dataControl.loadLastState(filepath), availabilityLock);
        if (!bookstore.getOrders().isEmpty() && bookstore.getOrders().getLast().getStatus() == Status.OPEN) {
            bookstore.setCurrentOrder(bookstore.getOrders().getLast());
        }
    }

    public void runWeb() {
        run("web");
        webView = new WebView(bookstore);
    }

    public void runConsole() {
        run("console");
        new Menu(bookstore);
    }

    public WebView getWebView() {
        return webView;
    }

    public DataControl getDataControl() {
        return dataControl;
    }

    public String getFilepath() {
        return filepath;
    }

    public static Properties getProperties() {
        return properties;
    }
}
