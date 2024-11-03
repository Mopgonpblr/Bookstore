package com.andersen.bookstore.model;

import com.andersen.bookstore.enums.Status;

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

    public Order(int id){
        this.id = id;
        this.status = Status.OPEN;
        this.openingTimestamp = LocalDateTime.now();
    }

    public void addBook(Book book){
        if (this.status == Status.OPEN) {
            this.books.add(book);
            this.totalPrice += book.getPrice();
        }
    }

    public void closeOrder(){
        this.closingTimestamp = LocalDateTime.now();
        this.status = Status.CLOSED;
    }

    public Status getStatus(){
        return status;
    }

    public List<Book> getBooks(){
        return books;
    }

    public String toString(){
        DecimalFormat numberFormat = new DecimalFormat("#.00");
        return this.id + " | " + numberFormat.format(this.totalPrice) + " | " + this.openingTimestamp + " | " + this.closingTimestamp + " | " + this.status.name();
    }

}
