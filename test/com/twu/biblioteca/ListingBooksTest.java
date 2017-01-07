package com.twu.biblioteca;

import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class ListingBooksTest {
    BibliotecaApp library = new BibliotecaApp();
    Book book1 = new Book("Programming in Java", "Gabriela Andrade", 2005);
    Book book2 = new Book("Programming in C#", "Gabriela Andrade", 2006);
    Book book3 = new Book("Test Driven Development", "Gabriela Andrade", 2007);
    ArrayList<Book> allBooks = new ArrayList<Book>();
    ArrayList<Book> notBorrowedBooks = new ArrayList<Book>();
    ArrayList<Book> borrowedBooks = new ArrayList<Book>();

    private void setUp() {
        book1.checkout();
        allBooks.add(book1);
        allBooks.add(book2);
        allBooks.add(book3);
        library.books = allBooks;
        notBorrowedBooks.add(book2);
        notBorrowedBooks.add(book3);
        borrowedBooks.add(book1);
    }

    private void tearDown() {
        allBooks = new ArrayList<Book>();
        notBorrowedBooks = new ArrayList<Book>();
        borrowedBooks = new ArrayList<Book>();
    }

    @Test
    public void listAllAvailableBooksTest() {
        setUp();
        ArrayList<Book> notBorrowedBooks = library.getAvailableBooks();
        assertEquals(this.notBorrowedBooks, notBorrowedBooks);
        tearDown();
    }

    @Test
    public void listEmptyLibrary() {
        library.books = allBooks;
        ArrayList<Book> notBorrowedBooks = library.getAvailableBooks();
        assertEquals(this.notBorrowedBooks, notBorrowedBooks);
        tearDown();
    }

    @Test
    public void listNullLibrary() {
        ArrayList<Book> notBorrowedBooks = library.getAvailableBooks();
        assertEquals(this.notBorrowedBooks, notBorrowedBooks);
        tearDown();
    }
}