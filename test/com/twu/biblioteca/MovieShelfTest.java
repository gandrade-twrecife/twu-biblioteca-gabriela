package com.twu.biblioteca;

import org.junit.Test;
import static org.junit.Assert.*;

public class MovieShelfTest {

    MovieShelf movieShelf;
    private final Movie movieMoulinRouge = new Movie("Moulin Rouge", 2001, "Baz Luhrmann", 9);
    private final Movie movieMononokeHime = new Movie("Mononoke Hime", 1997, "Hayao Miyazaki", 8);
    private final Movie movieGuardiansGalaxy = new Movie("Guardians of the Galaxy", 2014, "James Gunn", 7);
    final String userLogin = "gandrade";

    private void setUp() {
        movieShelf = new MovieShelf();
        movieShelf.add(movieMoulinRouge);
        movieShelf.add(movieMononokeHime);
        movieShelf.add(movieGuardiansGalaxy);
    }

    @Test
    public void addMoviesToMovieShelfTest() {
        movieShelf = new MovieShelf();
        movieShelf.add(movieMoulinRouge);
        assertEquals(1, movieShelf.getAmountOfItens());
        assertTrue(movieShelf.getMovieByName(movieMoulinRouge.getName()) != null);
    }

    @Test
    public void getAvailableMoviesTest() {
        setUp();
        movieShelf.movies.get(0).checkoutItem(userLogin);
        assertEquals(2, movieShelf.getAvailableItems().size());
    }

    @Test
    public void getBorrowedMoviesByUser() {
        setUp();
        movieShelf.movies.get(0).checkoutItem(userLogin);
        movieShelf.movies.get(1).checkoutItem(userLogin);
        assertEquals(2, movieShelf.getBorrowedItemsByUser(userLogin).size());
    }

    @Test
    public void getHeaderTest() {
        setUp();
        String name = "Name";
        String director = "Director";
        String year = "Year";
        String rating = "Rating";
        int sizeOfMovieIndexes = ((Integer) movieShelf.movies.size()).toString().length() + 2;
        String expected = "The movies available to check out are:\n" +
                Utilities.repeatedCharacter(sizeOfMovieIndexes, ' ') +
                name + Utilities.repeatedCharacter(Utilities.sizeOfMovieNames - name.length(), ' ') +
                director + Utilities.repeatedCharacter(Utilities.sizeOfMovieDirectors - director.length(), ' ') +
                year + " " + rating + "\n" +
                Utilities.repeatedCharacter(sizeOfMovieIndexes + Utilities.sizeOfMovieNames +
                        Utilities.sizeOfMovieDirectors + year.length() + 1 + rating.length(), '-');
        assertEquals(expected, movieShelf.getHeader());
    }
}
