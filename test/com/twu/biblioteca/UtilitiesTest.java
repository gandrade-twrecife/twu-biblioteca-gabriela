package com.twu.biblioteca;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UtilitiesTest {
    private final Book bookJava = new Book("Programming in Java", "Gabriela Andrade", 2005);
    private final Movie movieMononokeHime = new Movie("Mononoke Hime", 1997, "Hayao Miyazaki", 8);

    @Test
    public void formatNumbersToStringOfLenght1() {
        assertEquals("1", Utilities.formatNumbersEqualStringSize(1, 1));
    }

    @Test
    public void formatNumbersToStringOfSameLenght() {
        assertEquals("1", Utilities.formatNumbersEqualStringSize(1, 1));
        assertEquals("1 ", Utilities.formatNumbersEqualStringSize(1, 2));
        assertEquals("1  ", Utilities.formatNumbersEqualStringSize(1, 3));
        assertEquals("1   ", Utilities.formatNumbersEqualStringSize(1, 4));
    }

    @Test
    public void addCharsToTheRightTest() {
        String original = "teste";
        String expected = "teste     ";
        assertEquals(expected, Utilities.addCharsToTheRight(original, 5, ' '));
    }

    @Test
    public void addZeroCharsToTheRightTest() {
        String original = "teste";
        String expected = original;
        assertEquals(expected, Utilities.addCharsToTheRight(original, 0, ' '));
    }

    @Test
    public void showFormattedBookTest() {
        String expected = bookJava.getTitle() +
                Utilities.repeatedCharacter((Utilities.sizeOfBookTitles - bookJava.getTitle().length()), ' ') +
                bookJava.getAuthor() +
                Utilities.repeatedCharacter((Utilities.sizeOfBookAuthors - bookJava.getAuthor().length()), ' ') +
                ((Integer)bookJava.getYearPublished()).toString();
        assertEquals(expected, Utilities.formatItemToShowInList(bookJava));
    }

    @Test
    public void showFormattedMovieTest() {
        String expected = movieMononokeHime.getName() +
                Utilities.repeatedCharacter((Utilities.sizeOfMovieNames - movieMononokeHime.getName().length()), ' ') +
                movieMononokeHime.getDirector() +
                Utilities.repeatedCharacter((Utilities.sizeOfMovieDirectors - movieMononokeHime.getDirector().length()), ' ') +
                ((Integer)movieMononokeHime.getYear()).toString() + " " + ((Double)movieMononokeHime.getMovieRating()).toString();
        assertEquals(expected, Utilities.formatItemToShowInList(movieMononokeHime));
    }
}
