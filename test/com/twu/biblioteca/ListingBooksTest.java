package com.twu.biblioteca;

import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class ListingBooksTest {
    BibliotecaApp library = new BibliotecaApp();
    Book book1 = new Book("Programming in Java", "Gabriela Andrade", 2005);
    Book book2 = new Book("Programming in C#", "Gabriela Andrade", 2006);
    Book book3 = new Book("Test Driven Development", "Gabriela Andrade", 2007);
    ArrayList<Book> all_books = new ArrayList<Book>();
    ArrayList<Book> not_borrowed_books = new ArrayList<Book>();
    ArrayList<Book> borrowed_books = new ArrayList<Book>();

    private void setUp() {
        all_books.add(book1);
        book1.checkout("Customer A");
        all_books.add(book2);
        all_books.add(book3);
        library.books = all_books;
        not_borrowed_books.add(book2);
        not_borrowed_books.add(book3);
        borrowed_books.add(book1);
    }

    private void tearDown() {
        all_books = new ArrayList<Book>();
        not_borrowed_books = new ArrayList<Book>();
        borrowed_books = new ArrayList<Book>();
    }

    @Test
    public void listAllNotBorrowedTest() {
        setUp();
        ArrayList<Book> not_borrowed_books = library.getNotBorrowedBooks();
        assertEquals(this.not_borrowed_books, not_borrowed_books);
        tearDown();
    }

    @Test
    public void listBorrowedByCustomerTest() {
        setUp();
        ArrayList<Book> borrowed_by_me = library.getBooksBorrowedByCustomer("Customer A");
        ArrayList<Book> expected = new ArrayList<Book>(1);
        expected.add(book1);
        assertEquals(expected, borrowed_by_me);
        tearDown();
    }
}