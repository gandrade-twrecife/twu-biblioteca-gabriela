package com.twu.biblioteca;

import org.junit.Test;
import static org.junit.Assert.*;

public class MovieShelfTest {

    MovieShelf movies;
    private static String movieMoulinTitle = "Moulin Rouge";
    private static String movieMoulinDirector = "Baz Luhrmann";
    private static int movieMoulinYear = 2001;
    private static double movieMoulinRating = 9;
    private final Movie movieMoulinRouge = new Movie(movieMoulinTitle, movieMoulinYear, movieMoulinDirector, movieMoulinRating);
    private final Movie movieMononokeHime = new Movie("Mononoke Hime", 1997, "Hayao Miyazaki", 8);
    private final Movie movieGuardiansGalaxy = new Movie("Guardians of the Galaxy", 2014, "James Gunn", 7);
    final String userLogin = "gandrade";

    private void setUp() {
        movies = new MovieShelf();
        movies.add(movieMoulinRouge);
        movies.add(movieMononokeHime);
        movies.add(movieGuardiansGalaxy);
    }

    @Test
    public void addMoviesToMovieShelfTest() {
        movies = new MovieShelf();
        movies.add(movieMoulinRouge);
        assertEquals(1, movies.getAmountOfItens());
        assertTrue(movies.getMovieByName(movieMoulinRouge.getName()) != null);
    }

    @Test
    public void successfulCheckoutMovieTest() {
        setUp();
        String expected = movies.successfulCheckoutResponse;
        assertEquals(expected, movies.checkoutItem(userLogin, movieMoulinRouge));
    }

    @Test
    public void unsuccessfulCheckoutMovieTest() {
        setUp();
        movies.checkoutItem(userLogin, movieMoulinRouge);
        String expected = movies.unsuccessfulCheckoutResponse;
        assertEquals(expected, movies.checkoutItem(userLogin, movieMoulinRouge));
    }

    @Test
    public void itemTypeErrorCheckoutMovieTest() {
        setUp();
        String expected = movies.itemTypeError;
        Book book = new Book("", "", 0);
        assertEquals(expected, movies.checkoutItem(userLogin, book));
    }

    @Test
    public void successfulReturnMovieTest() {
        setUp();
        movies.checkoutItem(userLogin, movieMoulinRouge);
        String expected = movies.successfulReturnResponse;
        assertEquals(expected, movies.returnItem(movieMoulinRouge));
    }

    @Test
    public void unsuccessfulReturnMovieTest() {
        setUp();
        String expected = movies.unsuccessfulReturnResponse;
        assertEquals(expected, movies.returnItem(movieMoulinRouge));
    }

    @Test
    public void itemTypeErrorReturnMovieTest() {
        setUp();
        String expected = movies.itemTypeError;
        Book book = new Book("", "", 0);
        assertEquals(expected, movies.returnItem(book));
    }

    @Test
    public void getAvailableMoviesTest() {
        setUp();
        movies.checkoutItem(userLogin, movieMoulinRouge);
        assertEquals(2, movies.getAvailableItems().size());
    }

    @Test
    public void getBorrowedMoviesByUser() {
        setUp();
        movies.checkoutItem(userLogin, movieMoulinRouge);
        movies.checkoutItem(userLogin, movieMononokeHime);
        assertEquals(2, movies.getBorrowedItemsByUser(userLogin).size());
    }

    @Test
    public void showFormattedMovieTest() {
        setUp();
        String expected = movieMoulinTitle + Utilities.repeatedCharacter((MovieShelf.sizeOfMovieNames - movieMoulinTitle.length()), ' ') +
                movieMoulinDirector + Utilities.repeatedCharacter((MovieShelf.sizeOfMovieDirectors - movieMoulinDirector.length()), ' ') +
                movieMoulinYear + " " + movieMoulinRating;
        assertEquals(expected, movies.formatItemToShowInList(movieMoulinRouge));
    }

    @Test
    public void getHeaderTest() {
        setUp();
        String expected = "The movies available to check out are:\n" +
                Utilities.repeatedCharacter(((Integer) movies.movies.size()).toString().length() + 2, ' ') +
                "Name                                                        Director                                Year Rating\n" +
                "------------------------------------------------------------------------------------------------------------------";
        assertEquals(expected, movies.getHeader());
    }
}
