package com.andersen.bookstore;


import com.andersen.bookstore.model.Book;
import com.andersen.bookstore.services.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.utility.DockerImageName;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@SpringBootTest
public class BookServiceTest {
    @Container
    @ServiceConnection
    static PostgreSQLContainer postgres = new PostgreSQLContainer(DockerImageName.parse("postgres:15.1"))
            .withUsername("username")
            .withPassword("password")
            .withDatabaseName("bookstore");

    @Autowired
    BookService bookService;

    @Test
    void fetchBooks_returnNotNull() {
        assertNotNull(bookService.fetchBooks());
    }

    @Test
    void fetchBooks_returnListOfBooks() {
        assertInstanceOf(List.class, bookService.fetchBooks());
        assertInstanceOf(Book.class, bookService.fetchBooks().getFirst());
    }



}
