package com.andersen.bookstore;

import com.andersen.bookstore.model.Book;
import com.andersen.bookstore.model.Bookstore;
import com.andersen.bookstore.view.Menu;

import java.util.LinkedList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        Bookstore bookstore = new Bookstore(createLibrary(), new LinkedList<>());

        Menu menu = new Menu(bookstore);

        menu.showMainMenu();
    }

    private static List<Book> createLibrary() {
        List<Book> books = new LinkedList<>();
        books.add(new Book("Pride and Prejudice", "Jane Austin", 9.99));
        books.add(new Book("The Catcher in the Rye", "J.D. Salinger", 5.99));
        books.add(new Book("Animal Farm", "George Orwell", 7.99));
        books.add(new Book("Pan Tadeusz", "Adam Mickiewicz", 9.99));
        books.add(new Book("Eugene Onegin", "Alexander Pushkin", 0.49));
        return books;
    }
}