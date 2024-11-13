package com.andersen.bookstore.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Book {
    private final String title;
    private final String author;
    private final double price;
    private boolean isAvailable;

    @JsonCreator
    public Book(@JsonProperty("title") String title, @JsonProperty("author") String author,
                @JsonProperty("price") double price, @JsonProperty("isAvailable") boolean isAvailable) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.isAvailable = isAvailable;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }

    public boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String toString() {
        return title + " | " + author + " | " + price;
    }
}
