package com.twu.biblioteca;

import org.junit.Test;
import static org.junit.Assert.*;

public class BookShelfTest {
    private BookShelf bookShelf;
    private final Book bookJava = new Book("Programming in Java", "Gabriela Andrade", 2005);
    private final Book bookCSharp = new Book("Programming in C#", "Gabriela Andrade", 2006);
    private final Book bookTDD = new Book("Test Driven Development", "Gabriela Andrade", 2007);
    final String userLogin = "gandrade";

    private void setUp() {
        bookShelf = new BookShelf();
        bookShelf.add(bookJava);
        bookShelf.add(bookCSharp);
        bookShelf.add(bookTDD);
    }

    @Test
    public void addMoviesToMovieShelfTest() {
        bookShelf = new BookShelf();
        bookShelf.add(bookJava);
        assertEquals(1, bookShelf.getAmountOfItens());
        assertTrue(bookShelf.getBookByTitle(bookJava.getTitle()) != null);
    }

    @Test
    public void getAvailableMoviesTest() {
        setUp();
        bookShelf.books.get(0).checkoutItem(userLogin);
        assertEquals(2, bookShelf.getAvailableItems().size());
    }

    @Test
    public void getBorrowedMoviesByUser() {
        setUp();
        bookShelf.books.get(0).checkoutItem(userLogin);
        bookShelf.books.get(1).checkoutItem(userLogin);
        assertEquals(2, bookShelf.getBorrowedItemsByUser(userLogin).size());
    }

    @Test
    public void getHeaderTest() {
        setUp();
        String title = "Title";
        String author = "Author";
        String year = "Year";
        int sizeOfBookIndexes = ((Integer) bookShelf.books.size()).toString().length() + 2;
        String expected = "The books available to check out are:\n" +
                Utilities.repeatedCharacter(sizeOfBookIndexes, ' ') +
                title + Utilities.repeatedCharacter(Utilities.sizeOfBookTitles - title.length(), ' ') +
                author + Utilities.repeatedCharacter(Utilities.sizeOfBookAuthors - author.length(), ' ') +
                year + "\n" +
                Utilities.repeatedCharacter(sizeOfBookIndexes + Utilities.sizeOfBookTitles +
                        Utilities.sizeOfBookAuthors + year.length(), '-');
        assertEquals(expected, bookShelf.getHeader());
    }

    @Test
    public void addBooksToBookShelfTest() {
        bookShelf = new BookShelf();
        bookShelf.add(bookJava);
        assertEquals(1, bookShelf.getAmountOfItens());
        assertTrue(bookShelf.getBookByTitle(bookJava.getTitle()) != null);
    }
}
