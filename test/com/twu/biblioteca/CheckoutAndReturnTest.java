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
        boolean attempt_to_checkout = book.checkout();
        assertTrue(attempt_to_checkout);
        assertTrue(book.isBorrowed());
        tearDown();
    }

    @Test
    public void bookCheckoutBorrowedBookTest() {
        setUpBorrowedBooks();
        Book book = library.books.get(1);
        boolean attempt_to_checkout = book.checkout();
        assertFalse(attempt_to_checkout);
        assertTrue(book.isBorrowed());
        tearDown();
    }

    @Test
    public void bookReturnTest() {
        setUpBorrowedBooks();
        Book book = library.books.get(1);
        boolean attempt_to_return = book.returnBook();
        assertTrue(attempt_to_return);
        assertFalse(book.isBorrowed());
        tearDown();
    }


    @Test
    public void bookReturnAvailableBookTest() {
        setUpAvailableBooks();
        Book book = library.books.get(1);
        boolean attempt_to_return = book.returnBook();
        assertFalse(attempt_to_return);
        assertFalse(book.isBorrowed());
        tearDown();
    }

    @Test
    public void libraryCheckoutTest() {
        setUpAvailableBooks();
        Book book = library.books.get(1);
        assertFalse(book.isBorrowed());
        String checkout_message = library.checkoutBook(book);
        assertEquals("Thank you! Enjoy the book.", checkout_message);
        assertTrue(book.isBorrowed());
        tearDown();
    }

    @Test
    public void libraryCheckoutBorrowedBookTest() {
        setUpBorrowedBooks();
        Book book = library.books.get(1);
        assertTrue(book.isBorrowed());
        String attempt_to_checkout = library.checkoutBook(book);
        assertEquals("That book is not available.", attempt_to_checkout);
        assertTrue(book.isBorrowed());
        tearDown();
    }

    @Test
    public void libraryReturnTest() {
        setUpBorrowedBooks();
        Book book = library.books.get(1);
        String attempt_to_return = library.returnBook(book);
        assertEquals("Thank you for returning the book.", attempt_to_return);
        assertFalse(book.isBorrowed());
        tearDown();
    }

    @Test
    public void libraryReturnAvailableBookTest() {
        setUpAvailableBooks();
        Book book = library.books.get(1);
        String attempt_to_return = library.returnBook(book);
        assertEquals("That is not a valid book to return.", attempt_to_return);
        assertFalse(book.isBorrowed());
        tearDown();
    }
}
