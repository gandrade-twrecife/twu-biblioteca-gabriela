package com.twu.biblioteca;

public class Movie implements BibliotecaItem {
    private String name;
    private int year;
    private String director;
    private double movieRating;
    private boolean borrowed;
    private String borrowedTo;

    public String getName() {return name;}
    public int getYear() {return year;}
    public String getDirector() {return director;}
    public double getMovieRating() {return movieRating;}
    public String getBorrowedTo() {return borrowedTo;}

    public Movie(String name, int year, String director, double movieRating){
        this.name = name;
        this.year = year;
        this.director = director;
        this.movieRating = movieRating;
    }

    public boolean checkoutItem(String userLogin) {
        if (this.borrowed) {
            return false;
        }
        this.borrowed = true;
        this.borrowedTo = userLogin;
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
