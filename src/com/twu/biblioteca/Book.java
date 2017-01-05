package com.twu.biblioteca;

public class Book {
    public String title;
    public String author;
    public int year_published;
    private boolean borrowed;

    public Book(String title, String author, int year_published) {
        this.title = title;
        this.author = author;
        this.year_published = year_published;
        this.borrowed = false;
    }

    public boolean checkout() {
        if (this.borrowed) {
            return false;
        }
        this.borrowed = true;
        return true;
    }

    public boolean returnBook() {
        if (!this.borrowed) {
            return false;
        }
        this.borrowed = false;
        return true;
    }

    public boolean isBorrowed() {
        return borrowed;
    }

    public String bookDetails() {
        return "Author: " + author + ", published in: " + year_published;
    }
}
