package com.twu.biblioteca;

public class Book {
    public String title;
    public String author;
    public int year_of_publish;
    public boolean borrowed;
    public String borrowed_to;

    public Book(String title, String author, int year_of_publish) {
        this.title = title;
        this.author = author;
        this.year_of_publish = year_of_publish;
        this.borrowed = false;
        this.borrowed_to = null;
    }

    public void checkout(String customer) {
        this.borrowed = true;
        this.borrowed_to = customer;
    }

    public void returnBook() {
        this.borrowed = false;
        this.borrowed_to = null;
    }
}
