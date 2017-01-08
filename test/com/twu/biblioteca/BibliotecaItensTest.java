package com.twu.biblioteca;

import org.junit.Test;
import static org.junit.Assert.*;

public class BibliotecaItensTest {
    private Book book = new Book("Programming in Java", "Some nice author", 1999);
    private Movie movie = new Movie("Moulin Rouge", 1999, "Some nice director", 2000);
    private String userLogin = "gandrade";

    @Test
    public void checkoutAvailableBook() {
        assertTrue(book.checkoutItem(userLogin));
        assertEquals(userLogin, book.getBorrowedTo());
    }

    @Test
    public void checkoutBorrowedBook() {
        String otherUserLogin = "pdias";
        assertTrue(book.checkoutItem(otherUserLogin));
        assertFalse(book.checkoutItem(userLogin));
        assertEquals(otherUserLogin, book.getBorrowedTo());
    }

    @Test
    public void checkoutAvailableMovie() {
        assertTrue(movie.checkoutItem(userLogin));
        assertEquals(userLogin, movie.getBorrowedTo());
    }

    @Test
    public void checkoutBorrowedMovie() {
        String otherUserLogin = "pdias";
        assertTrue(movie.checkoutItem(otherUserLogin));
        assertFalse(movie.checkoutItem(userLogin));
        assertEquals(otherUserLogin, movie.getBorrowedTo());
    }

    @Test
    public void returnBorrowedBook() {
        assertTrue(book.checkoutItem(userLogin));
        assertTrue(book.returnItem());
    }

    @Test
    public void returnAvailableBook() {
        assertFalse(book.returnItem());
    }

    @Test
    public void returnBorrowedMovie() {
        assertTrue(movie.checkoutItem(userLogin));
        assertTrue(movie.returnItem());
    }

    @Test
    public void returnAvailableMovie() {
        assertFalse(movie.returnItem());
    }
}
