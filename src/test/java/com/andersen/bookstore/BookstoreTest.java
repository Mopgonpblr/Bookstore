package com.andersen.bookstore;

import com.andersen.bookstore.enums.Status;
import com.andersen.bookstore.model.Book;
import com.andersen.bookstore.model.Bookstore;
import com.andersen.bookstore.model.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BookstoreTest {

    private Bookstore bookstore;

    @BeforeEach()
    public void setup() {
        List<Order> orders = new LinkedList<>();
        List<Book> books = new LinkedList<>(List.of(new Book("1", "2", 3)));
        bookstore = new Bookstore(books, orders);
        bookstore.openOrder();
    }


    @Test
    public void openOrder_addNewOrder_currentOrderNotNull() {
        assertFalse(bookstore.getOrders().isEmpty());
        assertNotNull(bookstore.getCurrentOrder());
    }


    @Test
    public void cancelOrder_orderRemoved_currentOrderNull() {
        bookstore.cancelOrder();
        assertTrue(bookstore.getOrders().isEmpty());
        assertNull(bookstore.getCurrentOrder());
    }


    @Test
    public void completeOrder_orderStatusChanged_currentOrderNull() {
        bookstore.completeOrder();
        assertSame(bookstore.getOrders().getLast().getStatus(), Status.CLOSED);
        assertNull(bookstore.getCurrentOrder());
    }


    @Test
    public void getCurrentOrder_notNull(){
        assertNotNull(bookstore.getCurrentOrder());
    }


    @Test
    public void getOrders_notEmpty(){
        assertFalse(bookstore.getOrders().isEmpty());
    }
}