package com.andersen.bookstore.controller;

import com.andersen.bookstore.services.BookService;
import com.andersen.bookstore.services.OrderService;
import com.andersen.bookstore.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class Controller {

    private BookService bookService;
    private OrderService orderService;

    @Autowired
    public Controller(BookService bookService, OrderService orderService) {
        this.bookService = bookService;
        this.orderService = orderService;
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
        mv.addObject("bookList", bookList.toString());
        return mv;
    }

    @PostMapping(value = "/availability")
    public ModelAndView setAvailability(@RequestParam String action,
                                        @RequestParam String bookNumber,
                                        @RequestParam String isAvailable) {

        if ("submit".equals(action)) {
            bookService.updateBookAvailability(bookNumber, isAvailable);
        }
        return new ModelAndView("index");
    }

    @GetMapping(value = "/orders")
    public ModelAndView getOrders() {
        StringBuilder sb = new StringBuilder("\n");
        for (int i = 0; i < orderService.fetchOrders().size() / 10 + 1; i++) {
            sb.append("Page ").append(i + 1).append("<br>");
            for (int j = 0; j < 10 && i * 10 + j < orderService.fetchOrders().size(); j++) {
                sb.append(orderService.fetchOrders().get(i * 10 + j).toString()).append("<br>");
            }
        }
        ModelAndView mv = new ModelAndView("list");
        mv.addObject("list", sb.toString());
        return mv;
    }
}
