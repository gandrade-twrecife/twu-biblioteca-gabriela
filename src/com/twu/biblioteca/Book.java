package com.twu.biblioteca;

public class Book {
    private String title;
    private String author;
    private int year_published;
    private boolean borrowed;

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYearPublished() {
        return year_published;
    }

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
