package com.twu.biblioteca;

import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class CheckoutAndReturnTest {

    BibliotecaApp library = new BibliotecaApp();
    Book book1 = new Book("Programming in Java", "Gabriela Andrade", 2005);
    Book book2 = new Book("Programming in C#", "Gabriela Andrade", 2006);
    Book book3 = new Book("Test Driven Development", "Gabriela Andrade", 2007);
    ArrayList<Book> books;

    private void setUpAvailableBooks() {
        books = new ArrayList<Book>();
        books.add(book1);
        books.add(book2);
        books.add(book3);
        library.books = books;
    }

    private void setUpBorrowedBooks() {
        books = new ArrayList<Book>();
        books.add(book1);
        books.add(book2);
        books.add(book3);
        library.books = books;
        books.get(0).checkout();
        books.get(1).checkout();
        books.get(2).checkout();
    }

    private void tearDown() {
        books = null;
    }

    @Test
    public void bookCheckoutTest() {
        setUpAvailableBooks();
        Book book = library.books.get(1);
        boolean attemptToCheckout = book.checkout();
        assertTrue(attemptToCheckout);
        assertTrue(book.isBorrowed());
        tearDown();
    }

    @Test
    public void bookCheckoutBorrowedBookTest() {
        setUpBorrowedBooks();
        Book book = library.books.get(1);
        boolean attemptToCheckout = book.checkout();
        assertFalse(attemptToCheckout);
        assertTrue(book.isBorrowed());
        tearDown();
    }

    @Test
    public void bookReturnTest() {
        setUpBorrowedBooks();
        Book book = library.books.get(1);
        boolean attemptToReturn = book.returnBook();
        assertTrue(attemptToReturn);
        assertFalse(book.isBorrowed());
        tearDown();
    }


    @Test
    public void bookReturnAvailableBookTest() {
        setUpAvailableBooks();
        Book book = library.books.get(1);
        boolean attemptToReturn = book.returnBook();
        assertFalse(attemptToReturn);
        assertFalse(book.isBorrowed());
        tearDown();
    }

    @Test
    public void libraryCheckoutTest() {
        setUpAvailableBooks();
        Book book = library.books.get(1);
        assertFalse(book.isBorrowed());
        String checkoutMessage = library.checkoutBook(book);
        assertEquals("Thank you! Enjoy the book.", checkoutMessage);
        assertTrue(book.isBorrowed());
        tearDown();
    }

    @Test
    public void libraryCheckoutBorrowedBookTest() {
        setUpBorrowedBooks();
        Book book = library.books.get(1);
        assertTrue(book.isBorrowed());
        String attemptToCheckout = library.checkoutBook(book);
        assertEquals("That book is not available.", attemptToCheckout);
        assertTrue(book.isBorrowed());
        tearDown();
    }

    @Test
    public void libraryReturnTest() {
        setUpBorrowedBooks();
        Book book = library.books.get(1);
        String attemptToReturn = library.returnBook(book);
        assertEquals("Thank you for returning the book.", attemptToReturn);
        assertFalse(book.isBorrowed());
        tearDown();
    }

    @Test
    public void libraryReturnAvailableBookTest() {
        setUpAvailableBooks();
        Book book = library.books.get(1);
        String attemptToReturn = library.returnBook(book);
        assertEquals("That is not a valid book to return.", attemptToReturn);
        assertFalse(book.isBorrowed());
        tearDown();
    }
}
