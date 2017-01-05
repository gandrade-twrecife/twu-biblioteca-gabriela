package com.twu.biblioteca;

public class Book {
    public String title;
    public String author;
    public int year_published;
    public boolean borrowed;
    public String borrowed_to;

    public Book(String title, String author, int year_published) {
        this.title = title;
        this.author = author;
        this.year_published = year_published;
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

    public String bookDetails() {
        return "Author: " + author + ", published in: " + year_published;
    }
}
