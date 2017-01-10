package com.twu.biblioteca;

import java.util.ArrayList;

public class MovieShelf implements BibliotecaItemShelf {
    ArrayList<Movie> movies = new ArrayList<Movie>();
    public String successfulCheckoutResponse = "Thank you! Enjoy the movie.";
    public String unsuccessfulCheckoutResponse = "That movie is not available.";
    public String itemTypeError = "This item does not belong to this shelf.";
    public String successfulReturnResponse = "Thank you for returning the movie.";
    public String unsuccessfulReturnResponse = "That is not a valid movie to return.";
    public static String noBooksAvailableMessage = "There are no available movies to checkout.";
    public static int sizeOfMovieNames = 60;
    public static int sizeOfMovieDirectors = 40;

    public String checkoutItem(String userLogin, BibliotecaItem movie){
        if (movie instanceof Movie) {
            if (movie.checkoutItem(userLogin)) return successfulCheckoutResponse;
            else return unsuccessfulCheckoutResponse;
        }
        return itemTypeError;
    }

    public String returnItem(BibliotecaItem movie){
        if (movie instanceof Movie) {
            if (movie.returnItem()) return successfulReturnResponse;
            else return unsuccessfulReturnResponse;
        }
        return itemTypeError;
    }

    public ArrayList<? extends BibliotecaItem> getAvailableItems(){
        ArrayList<Movie> availableMovies = new ArrayList<Movie>();
        for (int i = 0; i < movies.size(); i++) {
            Movie currentMovie = movies.get(i);
            if (!currentMovie.isBorrowed()) {
                availableMovies.add(currentMovie);
            }
        }
        return availableMovies;
    }

    public ArrayList<? extends BibliotecaItem> getBorrowedItemsByUser(String userLogin) {
        ArrayList<Movie> borrowedMovies = new ArrayList<Movie>();
        for (int i = 0; i < movies.size(); i++) {
            Movie currentMovie = movies.get(i);
            if (currentMovie.isBorrowed()) {
                if (currentMovie.getBorrowedTo().equals(userLogin)) {
                    borrowedMovies.add(currentMovie);
                }
            }
        }
        return borrowedMovies;
    }

    public String formatItemToShowInList(BibliotecaItem item) {
        int sizeTitle = sizeOfMovieNames;
        int sizeAuthor = sizeOfMovieDirectors;
        Movie movie = (Movie)item;

        int spacesToAdd = (sizeTitle - movie.getName().length());
        String formattedMovie = Utilities.addCharsToTheRight(movie.getName(), spacesToAdd, ' ');

        spacesToAdd = (sizeAuthor - movie.getDirector().length());
        formattedMovie += Utilities.addCharsToTheRight(movie.getDirector(), spacesToAdd, ' ');

        formattedMovie += movie.getYear() + " " + movie.getMovieRating();

        return formattedMovie;
    }

    public String getHeader(){
        int amountOfBooks = getAvailableItems().size();
        String name = "Name";
        String director = "Director";
        String year = "Year";
        String rating = "Rating";
        if (amountOfBooks > 0) {
            int spacesToTheLeftOfHeaders = ((Integer) (amountOfBooks + 1)).toString().length() + 2;

            String columnsHeader = Utilities.repeatedCharacter(spacesToTheLeftOfHeaders, ' ')
                    + name + Utilities.repeatedCharacter(sizeOfMovieNames - name.length(), ' ')
                    + director + Utilities.repeatedCharacter(sizeOfMovieDirectors - director.length(), ' ')
                    + year + " "
                    + rating + "\n" + Utilities.repeatedCharacter(
                    spacesToTheLeftOfHeaders + sizeOfMovieNames + sizeOfMovieDirectors + year.length() + 1 + rating.length(),
                    '-');

            return "The movies available to check out are:\n" + columnsHeader;
        }
        return noBooksAvailableMessage;
    }

    public int getAmountOfItens() {
        return movies.size();
    }

    public void add(BibliotecaItem movie) {
        movies.add((Movie) movie);
    }

    public Movie getMovieByName(String name) {
        for (int i = 0; i < movies.size(); i++) {
            if (movies.get(i).getName().equals(name))
                return movies.get(i);
        }
        return null;
    }
}
