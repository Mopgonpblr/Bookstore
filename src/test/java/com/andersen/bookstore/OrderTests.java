package com.andersen.bookstore;

import com.andersen.bookstore.enums.Status;
import com.andersen.bookstore.model.Book;
import com.andersen.bookstore.model.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderTests {
    private Order order;

    @BeforeEach()
    public void setup() {
       order = new Order(1);
    }

    @Test
    public void AddBook_BookAdded(){
        Book book = new Book("1","2",3);
        order.addBook(book);
        assertEquals(order.getBooks().getLast(),book);
    }

    @Test
    public void CloseOrder_StatusClosed(){
        order.closeOrder();
        assertEquals(order.getStatus(), Status.CLOSED);
    }

}
