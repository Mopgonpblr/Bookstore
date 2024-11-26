package com.andersen.bookstore;

import com.andersen.bookstore.controller.SessionFactoryProvider;
import com.andersen.bookstore.model.Book;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional
    public List<Book> fetchBooks() {
        return bookRepository.findAllByOrderByIdAsc();
    }

    @Transactional
    public void updateBookAvailability(int id, boolean isAvailable) {
        bookRepository.updateAvailability(id + "", isAvailable + "");
    }

    @Transactional
    public void saveBook(Book book) {
        bookRepository.save(book);
    }
}
