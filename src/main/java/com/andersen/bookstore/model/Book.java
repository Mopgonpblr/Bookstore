package com.andersen.bookstore.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity(name = "library")
@Table(name = "library")
public class Book {

    @Id
    @GeneratedValue
    private int id;

    @Column
    private String title;

    @Column
    private String author;

    @Column
    private double price;

    @Column(name = "is_available")
    private boolean isAvailable;

    @JsonCreator
    public Book(@JsonProperty("id") int id, @JsonProperty("title") String title, @JsonProperty("author") String author,
                @JsonProperty("price") double price, @JsonProperty("isAvailable") boolean isAvailable) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
        this.isAvailable = isAvailable;
    }

    public Book() {

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
        return id + " | " + title + " | " + author + " | " + price + " | " + isAvailable;
    }
}
