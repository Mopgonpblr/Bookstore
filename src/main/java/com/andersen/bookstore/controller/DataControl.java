package com.andersen.bookstore.controller;

import com.andersen.bookstore.model.Bookstore;
import com.andersen.bookstore.model.Order;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class DataControl {
    public static Properties readProperties() {
        Properties properties = new Properties();
        try (InputStream fis = Controller.class.getClassLoader().getResourceAsStream("application.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return properties;
    }

    public static List<Order> loadLastState(String filepath) {
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

    public static void saveLastState(Bookstore bookstore, String filepath) {
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
