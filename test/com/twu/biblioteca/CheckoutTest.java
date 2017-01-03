package com.twu.biblioteca;


import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class CheckoutTest {

    BibliotecaApp library = new BibliotecaApp();
    Book book1 = new Book("Programming in Java", "Gabriela Andrade", 2005);
    Book book2 = new Book("Programming in C#", "Gabriela Andrade", 2006);
    Book book3 = new Book("Test Driven Development", "Gabriela Andrade", 2007);
    ArrayList<Book> books;

    private void setUp() {
        books = new ArrayList<Book>();
        books.add(book1);
        books.add(book2);
        books.add(book3);
        library.books = books;
    }

    private void tearDown() {
        books = null;
    }

    @Test
    public void bookCheckoutTest() {
        setUp();
        Book book = library.books.get(1);
        assertFalse(book.borrowed);
        assertNull(book.borrowed_to);
        book.checkout("Customer A");
        assertTrue(book.borrowed);
        assertEquals("Customer A", book.borrowed_to);
        tearDown();
    }

    @Test
    public void bookReturnTest() {
        setUp();
        Book book = library.books.get(1);
        book.checkout("Customer A");
        assertTrue(book.borrowed);
        assertEquals("Customer A", book.borrowed_to);
        book.returnBook();
        assertFalse(book.borrowed);
        assertNull(book.borrowed_to);
        tearDown();
    }
}
