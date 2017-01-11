package com.twu.biblioteca;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ListItemsMenuOptionTest {

    private final Book bookJava = new Book("Programming in Java", "Gabriela Andrade", 2005);
    private final Book bookCSharp = new Book("Programming in C#", "Gabriela Andrade", 2006);
    private final Book bookTDD = new Book("Test Driven Development", "Gabriela Andrade", 2007);

    private final Movie movieMoulinRouge = new Movie("Moulin Rouge", 2001, "Baz Luhrmann", 9);
    private final Movie movieMononokeHime = new Movie("Mononoke Hime", 1997, "Hayao Miyazaki", 8);
    private final Movie movieGuardiansGalaxy = new Movie("Guardians of the Galaxy", 2014, "James Gunn", 7);

    @Test
    public void createCheckoutBookTest() {
        ListItemsMenuOption option = new ListItemsMenuOption(ListItemsMenuOption.typeBooksToCheckout);
        assertEquals("1", option.getOption());
        assertEquals("1: List Books.", option.getTitle());
        assertEquals(ListItemsMenuOption.msgBooksToCheckout, option.getMessageBeforeUserInput());
    }

    @Test
    public void createReturnBookTest() {
        ListItemsMenuOption option = new ListItemsMenuOption(ListItemsMenuOption.typeBooksToReturn);
        assertEquals("2", option.getOption());
        assertEquals("2: Return Books.", option.getTitle());
        assertEquals(ListItemsMenuOption.msgBooksToReturn, option.getMessageBeforeUserInput());
    }

    @Test
    public void createListMovieTest() {
        ListItemsMenuOption option = new ListItemsMenuOption(ListItemsMenuOption.typeMoviesToCheckout);
        assertEquals("3", option.getOption());
        assertEquals("3: List Movies.", option.getTitle());
        assertEquals(ListItemsMenuOption.msgMoviesToCheckout, option.getMessageBeforeUserInput());
    }

    @Test
    public void createReturnMovieTest() {
        ListItemsMenuOption option = new ListItemsMenuOption(ListItemsMenuOption.typeMoviesToReturn);
        assertEquals("4", option.getOption());
        assertEquals("4: Return Movies.", option.getTitle());
        assertEquals(ListItemsMenuOption.msgMoviesToReturn, option.getMessageBeforeUserInput());
    }

    @Test
    public void checkTypeOfOptionTest() {
        ArrayList<ListItemsMenuOption> allOptions = setUpAllTypesOfListMenuOptions();
        assertEquals(ListItemsMenuOption.msgBooksToCheckout, allOptions.get(0).getMessageBeforeUserInput());
        assertEquals(ListItemsMenuOption.msgBooksToReturn, allOptions.get(1).getMessageBeforeUserInput());
        assertEquals(ListItemsMenuOption.msgMoviesToCheckout, allOptions.get(2).getMessageBeforeUserInput());
        assertEquals(ListItemsMenuOption.msgMoviesToReturn, allOptions.get(3).getMessageBeforeUserInput());
    }

    @Test
    public void getParentTest() {
        ArrayList<ListItemsMenuOption> allOptions = setUpAllTypesOfListMenuOptions();
        PredefinedMenuOption expectedParent = new PredefinedMenuOption();
        for (int i = 0; i < allOptions.size(); i++) {
            MenuOptionInterface parent = allOptions.get(i).getParent();
            assertEquals(expectedParent.getOption(), parent.getOption());
            assertEquals(expectedParent.getTitle(), parent.getTitle());
            assertEquals(expectedParent.getMessageBeforeUserInput(), parent.getMessageBeforeUserInput());
        }
    }

    @Test public void generateBookOptionsByListTest() {
        BookShelf bookShelf = setUpBookShelf();
        ArrayList<ListItemsMenuOption> allOptions = setUpAllTypesOfListMenuOptions();
        for (int i = 0; i < allOptions.size(); i++) {
            ArrayList<MenuOptionInterface> childOptions = allOptions.get(i).generateOptions(bookShelf, null, null);
            for (int j = 0; j < childOptions.size(); j++) {
                int sizeOfBookIndexes = ((Integer)childOptions.size()).toString().length();
                if (j==0) {
                    assertEquals(Utilities.formatNumbersEqualStringSize(j+1, sizeOfBookIndexes)
                            + ": " + Utilities.formatBookToShowInList(bookJava), childOptions.get(j).getTitle());
                }
                else if (j==1) {
                    assertEquals(Utilities.formatNumbersEqualStringSize(j+1, sizeOfBookIndexes)
                            + ": " + Utilities.formatBookToShowInList(bookCSharp), childOptions.get(j).getTitle());
                }
                else if (j==2) {
                    assertEquals(Utilities.formatNumbersEqualStringSize(j+1, sizeOfBookIndexes)
                            + ": " + Utilities.formatBookToShowInList(bookTDD), childOptions.get(j).getTitle());
                }
                else if (j==3) {
                    assertEquals(PredefinedMenuOption.backTitle, childOptions.get(j).getTitle());
                }
            }
        }
    }

    @Test public void generateMovieOptionsByListTest() {
        MovieShelf movieShelf = setUpMovieShelf();
        ArrayList<ListItemsMenuOption> allOptions = setUpAllTypesOfListMenuOptions();
        for (int i = 0; i < allOptions.size(); i++) {
            ArrayList<MenuOptionInterface> childOptions = allOptions.get(i).generateOptions(movieShelf, null, null);
        }
    }

    private BookShelf setUpBookShelf() {
        BookShelf bookShelf = new BookShelf();
        bookShelf.add(bookJava);
        bookShelf.add(bookCSharp);
        bookShelf.add(bookTDD);
        return bookShelf;
    }

    private MovieShelf setUpMovieShelf() {
        MovieShelf movieShelf = new MovieShelf();
        movieShelf.add(movieMoulinRouge);
        movieShelf.add(movieMononokeHime);
        movieShelf.add(movieGuardiansGalaxy);
        return movieShelf;
    }

    private ArrayList<ListItemsMenuOption> setUpAllTypesOfListMenuOptions() {
        ArrayList<ListItemsMenuOption> allTypesOfListOptions = new ArrayList<ListItemsMenuOption>();
        allTypesOfListOptions.add(new ListItemsMenuOption(ListItemsMenuOption.typeBooksToCheckout));
        allTypesOfListOptions.add(new ListItemsMenuOption(ListItemsMenuOption.typeBooksToReturn));
        allTypesOfListOptions.add(new ListItemsMenuOption(ListItemsMenuOption.typeMoviesToCheckout));
        allTypesOfListOptions.add(new ListItemsMenuOption(ListItemsMenuOption.typeMoviesToReturn));
        return allTypesOfListOptions;
    }
}
