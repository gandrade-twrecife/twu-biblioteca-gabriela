package com.twu.biblioteca;

import org.junit.Test;
import static org.junit.Assert.*;

public class BookShelfTest {
    private BookShelf books;
    private final Book bookJava = new Book("Programming in Java", "Gabriela Andrade", 2005);
    private final Book bookCSharp = new Book("Programming in C#", "Gabriela Andrade", 2006);
    private final Book bookTDD = new Book("Test Driven Development", "Gabriela Andrade", 2007);
    private final String userLogin = "gandrade";

    private void setUp() {
        books = new BookShelf();
        books.add(bookJava);
        books.add(bookCSharp);
        books.add(bookTDD);
    }

    @Test
    public void addBooksToBookShelfTest() {
        books = new BookShelf();
        books.add(bookJava);
        assertEquals(1, books.getAmountOfItens());
        assertTrue(books.getBookByTitle(bookJava.getTitle()) != null);
    }

    @Test
    public void successfulCheckoutBookTest() {
        setUp();
        String expected = books.successfulCheckoutResponse;
        assertEquals(expected, books.checkoutItem(userLogin, bookJava));
    }

    @Test
    public void unsuccessfulCheckoutBookTest() {
        setUp();
        books.checkoutItem(userLogin, bookJava);
        String expected = books.unsuccessfulCheckoutResponse;
        assertEquals(expected, books.checkoutItem(userLogin, bookJava));
    }

    @Test
    public void itemTypeErrorCheckoutBookTest() {
        setUp();
        String expected = books.itemTypeError;
        Movie movie = new Movie("", 0, "", 0);
        assertEquals(expected, books.checkoutItem(userLogin, movie));
    }

    @Test
    public void successfulReturnBookTest() {
        setUp();
        books.checkoutItem(userLogin, bookJava);
        String expected = books.successfulReturnResponse;
        assertEquals(expected, books.returnItem(bookJava));
    }

    @Test
    public void unsuccessfulReturnBookTest() {
        setUp();
        String expected = books.unsuccessfulReturnResponse;
        assertEquals(expected, books.returnItem(bookJava));
    }

    @Test
    public void itemTypeErrorReturnBookTest() {
        setUp();
        String expected = books.itemTypeError;
        Movie movie = new Movie("", 0, "", 0);
        assertEquals(expected, books.returnItem(movie));
    }

    @Test
    public void getAvailableBooksTest() {
        setUp();
        books.checkoutItem(userLogin, bookJava);
        assertEquals(2, books.getAvailableItems().size());
    }

    @Test
    public void getBorrowedBooksByUser() {
        setUp();
        books.checkoutItem(userLogin, bookJava);
        books.checkoutItem(userLogin, bookCSharp);
        assertEquals(2, books.getBorrowedItemsByUser(userLogin).size());
    }

    @Test
    public void showFormattedBookTest() {
        setUp();
        String expected = "Programming in Java                                                   Gabriela Andrade                                  2005";
        assertEquals(expected, books.formatItemToShowInList(bookJava));
    }
}
