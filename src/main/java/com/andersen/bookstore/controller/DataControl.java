package com.andersen.bookstore.controller;

import com.andersen.bookstore.model.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.*;
import java.sql.*;
import java.util.*;

public class DataControl {
    private static final String SELECT = "select * from library";
    public static final Properties  PROPERTIES = new Properties();

    static{
        try (InputStream fis = Controller.class.getClassLoader().getResourceAsStream("application.properties")) {
            PROPERTIES.load(fis);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Order> loadLastState(String filepath) {
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

    public void saveLastState(Bookstore bookstore, String filepath) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            File file = new File(filepath);
            objectMapper.writeValue(file, bookstore.getOrders());
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    public List<Book> fetchBooks() {

        List<Book> books = new LinkedList<>();

        try (Connection con = HikariCPDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(SELECT);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                books.add(new Book(rs.getString("title"),
                        rs.getString("author"),
                        rs.getDouble("price"),
                        rs.getBoolean("is_available")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return books;
    }
}
