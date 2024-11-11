package com.andersen.bookstore;

import com.andersen.bookstore.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookTest {

    private Book book;
    private String title;
    private String author;
    private double price;
    private boolean isAvailable;

    @BeforeEach()
    public void setup() {
        title = "Test Title";
        author = "Test Author";
        price = 1.99;
        isAvailable = true;
        book = new Book(title,author,price, isAvailable);
    }


    @Test
    public void getTitle_returnTestTitle(){
        assertEquals(book.getTitle(),title);
    }


    @Test
    public void getAuthor_returnTestAuthor(){
        assertEquals(book.getAuthor(),author);
    }


    @Test
    public void getPrice_returnTestPrice(){
        assertEquals(book.getPrice(),price);
    }
}
