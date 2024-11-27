package com.andersen.bookstore.services;

import com.andersen.bookstore.repositories.BookRepository;
import com.andersen.bookstore.model.Book;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void updateBookAvailability(String bookNumber, String isAvailable) {


        if ("yes".equals(isAvailable)) {
            bookRepository.updateAvailability(bookNumber, "true");
        } else if ("no".equals(isAvailable)) {
            bookRepository.updateAvailability(bookNumber, "false");
        }

    }

    @Transactional
    public void saveBook(Book book) {
        bookRepository.save(book);
    }
}
