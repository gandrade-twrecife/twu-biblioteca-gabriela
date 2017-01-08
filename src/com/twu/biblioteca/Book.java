package com.twu.biblioteca;

public class Book implements BibliotecaItem {
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

    public boolean checkoutItem() {
        if (this.borrowed) {
            return false;
        }
        this.borrowed = true;
        return true;
    }

    public boolean returnItem() {
        if (!this.borrowed) {
            return false;
        }
        this.borrowed = false;
        return true;
    }

    public boolean isBorrowed() {
        return borrowed;
    }
}
