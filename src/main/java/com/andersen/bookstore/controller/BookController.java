package com.andersen.bookstore.controller;

import com.andersen.bookstore.BookService;
import com.andersen.bookstore.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    public ModelAndView home() {
        System.out.println("Going home...");
        return new ModelAndView("index");
    }

    @GetMapping(value = "/availability")
    public ModelAndView getBooks() {
        StringBuilder bookList = new StringBuilder();
        for (Book book : bookService.fetchBooks()) {
            bookList.append("<br>").append(book);
        }
        ModelAndView mv = new ModelAndView("availability");
        mv.addObject("bookList",bookList.toString());
        return mv;
    }

    @PostMapping(value = "/availability")
    public ModelAndView setAvailability(@RequestParam String action,
                                        @RequestParam String bookNumber,
                                        @RequestParam String isAvailable) {

        int number = Integer.parseInt(bookNumber);
        if ("submit".equals(action)) {
            if ("yes".equals(isAvailable)) {
                bookService.updateBookAvailability(number, true);
            } else if ("no".equals(isAvailable)) {
                bookService.updateBookAvailability(number, false);
            }
        }
        return new ModelAndView("index");
    }
}
