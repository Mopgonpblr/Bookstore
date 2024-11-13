package com.andersen.bookstore;

import com.andersen.bookstore.controller.DataControl;
import com.andersen.bookstore.model.Book;
import com.andersen.bookstore.model.Bookstore;
import com.andersen.bookstore.model.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DataControlTest {

    private Bookstore bookstore;
    private List<Book> books;
    private final String rightPath = "src/main/resources/Data.txt";
    private final String wrongPath = "wrong path";

    @BeforeEach()
    public void setup() {
        List<Order> orders = new LinkedList<>();
        books = new LinkedList<>(List.of(new Book("1", "2", 3, true)));
        bookstore = new Bookstore(books, orders, true);
    }

    @Test
    public void readProperties_notEmpty() {
        assertFalse(DataControl.readProperties().isEmpty());
    }

    @Test
    public void loadLastState_rightPath_notEmpty() {
        assertFalse(DataControl.loadLastState(rightPath).isEmpty());
    }

    @Test
    public void loadLastState_wrongPath_empty() {
        assertTrue(DataControl.loadLastState(wrongPath).isEmpty());
    }

    @Test
    public void saveLastState_rightPath_equal() {
        List<Order> orders = DataControl.loadLastState(rightPath);
        bookstore = new Bookstore(books, orders, true);
        bookstore.openOrder();
        bookstore.completeOrder();
        DataControl.saveLastState(bookstore, rightPath);

        List<Order> orders2 = DataControl.loadLastState(rightPath);
        assertEquals(orders2.getLast().getId(), orders.getLast().getId());
        assertEquals(orders2.getLast().getOpeningTimestamp(), orders.getLast().getOpeningTimestamp());
        assertEquals(orders2.getLast().getClosingTimestamp(), orders.getLast().getClosingTimestamp());

    }

    @Test
    public void saveLastState_wrongPath_notEqual() {
        List<Order> orders = DataControl.loadLastState(rightPath);
        bookstore = new Bookstore(books, orders, true);
        bookstore.openOrder();
        bookstore.completeOrder();
        DataControl.saveLastState(bookstore, wrongPath);

        List<Order> orders2 = DataControl.loadLastState(rightPath);
        assertNotEquals(orders2.getLast().getId(), orders.getLast().getId());
        assertNotEquals(orders2.getLast().getOpeningTimestamp(), orders.getLast().getOpeningTimestamp());
        assertNotEquals(orders2.getLast().getClosingTimestamp(), orders.getLast().getClosingTimestamp());

    }

}
