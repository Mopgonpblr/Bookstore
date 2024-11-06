package com.andersen.bookstore;

import com.andersen.bookstore.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookTests {

    private Book book;
    private String title;
    private String author;
    private double price;

    @BeforeEach()
    public void setup() {
        title = "Test Title";
        author = "Test Author";
        price = 1.99;
        book = new Book(title,author,price );
    }

    @Test
    public void GetTitle_ReturnTestTitle(){
        assertEquals(book.getTitle(),title);
    }

    @Test
    public void GetAuthor_ReturnTestAuthor()
    {
        assertEquals(book.getAuthor(),author);
    }
    @Test
    public void GetPrice_ReturnTestPrice()
    {
        assertEquals(book.getPrice(),price);
    }
}
