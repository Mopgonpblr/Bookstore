package com.andersen.bookstore.controller;

import com.andersen.bookstore.enums.Status;
import com.andersen.bookstore.model.Book;
import com.andersen.bookstore.model.Bookstore;
import com.andersen.bookstore.model.Order;
import com.andersen.bookstore.view.Menu;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class Controller {

    private String filepath;
    private boolean availabilityLock;


    public void run() {
        readProperties();

        Bookstore bookstore = new Bookstore(createLibrary(), loadLastState(filepath), availabilityLock);
        if (!bookstore.getOrders().isEmpty() && bookstore.getOrders().getLast().getStatus() == Status.OPEN) {
            bookstore.setCurrentOrder(bookstore.getOrders().getLast());
        }

        Menu menu = new Menu(bookstore);

        menu.showMainMenu();

        saveLastState(bookstore, filepath);

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

    private void readProperties() {
        Properties properties = new Properties();
        try (InputStream fis = Controller.class.getClassLoader().getResourceAsStream("application.properties")) {
            properties.load(fis);
            filepath = properties.getProperty("filepath");
            availabilityLock = Boolean.parseBoolean(properties.getProperty("availabilityLock"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private List<Order> loadLastState(String filepath) {
        List<Order> orders = new LinkedList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            Scanner scanner = new Scanner(new File(filepath));
            if (scanner.hasNextLine()) {
                orders = objectMapper.readValue(scanner.nextLine(), new TypeReference<>() {
                });
            }
            scanner.close();
        } catch (JsonProcessingException | FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return orders;
    }

    private void saveLastState(Bookstore bookstore, String filepath) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            File file = new File(filepath);
            objectMapper.writeValue(file, bookstore.getOrders());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
