package com.andersen.bookstore.model;

public class Book {
    private final String title;
    private final String author;
    private final double price;

    public Book(String title, String author, double price){
        this.title = title;
        this.author = author;
        this.price = price;
    }

    public String getTitle(){
        return title;
    }

    public String getAuthor(){
        return author;
    }

    public double getPrice(){
        return price;
    }

    public String toString(){
        return title + " | " + author + " | " + price;
    }
}
