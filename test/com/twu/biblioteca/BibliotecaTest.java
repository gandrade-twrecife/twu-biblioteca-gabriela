package com.twu.biblioteca;

import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class BibliotecaTest {
    BibliotecaApp library;
    ArrayList<Book> books;
    Book bookProgrammingInJava = new Book("Programming in Java", "Gabriela Andrade", 2005);
    Book bookProgrammingInCSharp = new Book("Programming in C#", "Gabriela Andrade", 2006);
    Book bookTDD = new Book("Test Driven Development", "Gabriela Andrade", 2007);

    @Test
    public void addCharsToTheLeftTest() {
        String original = "teste";
        String expected = "     teste";
        assertEquals(expected, library.addCharsToTheLeft(original, 5, ' '));
    }

    @Test
    public void addZeroCharsToTheLeftTest() {
        String original = "teste";
        String expected = "teste";
        assertEquals(expected, library.addCharsToTheLeft(original, 0, ' '));
    }

    @Test
    public void addCharsToTheRightTest() {
        String original = "teste";
        String expected = "teste     ";
        assertEquals(expected, library.addCharsToTheRight(original, 5, ' '));
    }

    @Test
    public void showFormattedBookTest() {
        String expected = "Programming in Java                                                   Gabriela Andrade                                  2005";
        assertEquals(expected, library.showFormattedBook(bookProgrammingInJava));
    }

    @Test
    public void showFormattedBookListTest() {
        setUp();
        String[] expected = {
                "1: Programming in Java                                                   Gabriela Andrade                                  2005",
                "2: Programming in C#                                                     Gabriela Andrade                                  2006",
                "3: Test Driven Development                                               Gabriela Andrade                                  2007"};
        assertArrayEquals(expected, library.showFormattedBookList(books));
    }

    @Test
    public void formatNumbersToStringOfLenght1() {
        assertEquals("1", library.formatNumbersEqualStringSize(1, 1));
    }

    @Test
    public void showFormattedBookListWithHugeAmountOfBooks() {
        setUpHugeAmountOfSameBook();
        int indexToLookUp = 99;
        String expected = indexToLookUp + " : Programming in Java                                                   Gabriela Andrade                                  2005";
        assertEquals(expected, library.showFormattedBookList(books)[indexToLookUp - 1]);
    }

    @Test
    public void formatNumbersToStringOfSameLenght() {
        assertEquals("1", library.formatNumbersEqualStringSize(1, 1));
        assertEquals("1 ", library.formatNumbersEqualStringSize(1, 2));
        assertEquals("1  ", library.formatNumbersEqualStringSize(1, 3));
        assertEquals("1   ", library.formatNumbersEqualStringSize(1, 4));
    }

    private void setUpHugeAmountOfSameBook() {
        library = new BibliotecaApp();
        books = new ArrayList<Book>();
        for (int i = 0; i < 120; i++) {
            books.add(bookProgrammingInJava);
        }
        library.books = books;
    }

    private void setUp() {
        library = new BibliotecaApp();
        books = new ArrayList<Book>();
        books.add(bookProgrammingInJava);
        books.add(bookProgrammingInCSharp);
        books.add(bookTDD);
        library.books = books;
    }
}