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
    private List<Book> books;

    @BeforeEach()
    public void setup() {
        List<Order> orders = new LinkedList<>();
        books = new LinkedList<>(List.of(new Book(0,"1", "2", 3, true)));
        bookstore = new Bookstore(books, orders, true);
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
        assertSame(Status.CLOSED, bookstore.getOrders().getLast().getStatus());
        assertNull(bookstore.getCurrentOrder());
    }

    @Test
    public void setBookAvailability_false_isAvailableFalse(){
        bookstore.setBookAvailability(1,false);
        assertFalse(books.getFirst().getIsAvailable());
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