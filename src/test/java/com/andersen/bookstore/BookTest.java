package com.andersen.bookstore;

import com.andersen.bookstore.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BookTest {

    private Book book;
    private int id;
    private String title;
    private String author;
    private double price;
    private boolean isAvailable;

    @BeforeEach()
    public void setup() {
        id = 1;
        title = "Test Title";
        author = "Test Author";
        price = 1.99;
        isAvailable = true;
        book = new Book(id,title, author, price, isAvailable);
    }

    @Test
    public void getTitle_returnTestTitle() {
        assertEquals(book.getTitle(), title);
    }


    @Test
    public void getAuthor_returnTestAuthor() {
        assertEquals(book.getAuthor(), author);
    }


    @Test
    public void getPrice_returnTestPrice() {
        assertEquals(book.getPrice(), price);
    }

    @Test
    public void getIsAvailable_returnTestIsAvailable() {
        assertTrue(book.getIsAvailable());
    }

    @Test
    public void setIsAvailable_false_IsAvailableIsFalse() {
        book.setIsAvailable(false);
        assertFalse(book.getIsAvailable());
    }
}
