package com.andersen.bookstore.model;

import com.andersen.bookstore.enums.Status;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class Order {
    private final int id;
    private final List<Book> books = new LinkedList<>();
    private double totalPrice;
    private final LocalDateTime openingTimestamp;
    private LocalDateTime closingTimestamp;
    private Status status;

    @JsonCreator
    public Order(@JsonProperty("id") int id) {
        this.id = id;
        this.status = Status.OPEN;
        this.openingTimestamp = LocalDateTime.now();
    }

    public void addBook(Book book) {
        if (this.status == Status.OPEN) {
            this.books.add(book);
            this.totalPrice += book.getPrice();
        }
    }

    public void closeOrder() {
        this.closingTimestamp = LocalDateTime.now();
        this.status = Status.CLOSED;
    }

    public int getId() {
        return id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public LocalDateTime getOpeningTimestamp() {
        return openingTimestamp;
    }

    public LocalDateTime getClosingTimestamp() {
        return closingTimestamp;
    }

    public Status getStatus() {
        return status;
    }

    public List<Book> getBooks() {
        return books;
    }


    public String toString() {
        DecimalFormat numberFormat = new DecimalFormat("#.00");
        return this.id + " | " + numberFormat.format(this.totalPrice) + " | " + this.openingTimestamp + " | " + this.closingTimestamp + " | " + this.status.name();
    }

}
