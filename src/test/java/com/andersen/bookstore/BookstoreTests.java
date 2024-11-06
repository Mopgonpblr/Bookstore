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

public class BookstoreTests {

    private Bookstore bookstore;

    @BeforeEach()
    public void setup() {
        List<Order> orders = new LinkedList<>();
        List<Book> books = new LinkedList<>(List.of(new Book("1", "2", 3)));
        bookstore = new Bookstore(books, orders);
        bookstore.openOrder();
    }

    @Test
    public void OpenOrder_AddNewOrder_CurrentOrderNotNull() {
        assertFalse(bookstore.getOrders().isEmpty());
        assertNotNull(bookstore.getCurrentOrder());
    }

    @Test
    public void CancelOrder_OrderRemoved_CurrentOrderNull() {
        bookstore.cancelOrder();
        assertTrue(bookstore.getOrders().isEmpty());
        assertNull(bookstore.getCurrentOrder());
    }

    @Test
    public void CompleteOrder_OrderStatusChanged_CurrentOrderNull() {
        bookstore.completeOrder();
        assertSame(bookstore.getOrders().getLast().getStatus(), Status.CLOSED);
        assertNull(bookstore.getCurrentOrder());
    }

    @Test
    public void GetCurrentOrder_NotNull(){
        assertNotNull(bookstore.getCurrentOrder());
    }

    @Test
    public void GetOrders_NotEmpty(){
        assertFalse(bookstore.getOrders().isEmpty());
    }
}