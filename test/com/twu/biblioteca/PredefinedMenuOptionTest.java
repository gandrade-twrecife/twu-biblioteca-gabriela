package com.twu.biblioteca;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PredefinedMenuOptionTest {
    PredefinedMenuOption menu;
    BibliotecaApp library = new BibliotecaApp();
    Book bookProgrammingInJava = new Book("Programming in Java", "Gabriela Andrade", 2005);
    Book bookProgrammingInCSharp = new Book("Programming in C#", "Gabriela Andrade", 2006);
    Book bookTDD = new Book("Test Driven Development", "Gabriela Andrade", 2007);
    String testOption = "Test option";
    String testTitle = "Test title";
    String testMessage = "Test message";

    @Test
    public void getRootTest() {
        PredefinedMenuOption option = new PredefinedMenuOption();
        assertEquals(PredefinedMenuOption.rootMessage, option.getMessageBeforeUserInput());
    }

    @Test
    public void getPredefinedOptionTest() {
        PredefinedMenuOption option = new PredefinedMenuOption(testOption, testTitle, testMessage);
        assertEquals(testOption, option.getOption());
        assertEquals(testTitle, option.getTitle());
        assertEquals(testMessage, option.getMessageBeforeUserInput());
    }

    @Test
    public void returnParentOfRootOptionTest() {
        PredefinedMenuOption option = new PredefinedMenuOption();
        assertNull(option.getParent());
    }

    @Test
    public void returnParentOfOtherPredefinedOptionTest() {
        PredefinedMenuOption option = new PredefinedMenuOption(testOption, testTitle, testMessage);
        assertNull(option.getParent());
    }

    @Test
    public void createQuitTest() {
        PredefinedMenuOption option = PredefinedMenuOption.createQuitOption();
        assertEquals(PredefinedMenuOption.quitOption, option.getOption());
        assertEquals(PredefinedMenuOption.quitTitle, option.getTitle());
        assertNull(option.getParent());
        assertNull(option.getMessageBeforeUserInput());
    }

    @Test
    public void createBackTest() {
        PredefinedMenuOption option = PredefinedMenuOption.createBackOption();
        assertEquals(PredefinedMenuOption.backOption, option.getOption());
        assertEquals(PredefinedMenuOption.backTitle, option.getTitle());
        assertNull(option.getParent());
        assertNull(option.getMessageBeforeUserInput());
    }

    @Test
    public void printRootOptionTest() {
        PredefinedMenuOption option = new PredefinedMenuOption();
        String expected = PredefinedMenuOption.rootMessage +
                "1: List Books." + "\n" +
                "2: Return Books." + "\n" +
                "3: List Movies." + "\n" +
                "4: Return Movies." + "\n" +
                PredefinedMenuOption.quitTitle;
        assertEquals(expected, option.printOptions(option.generateOptions(null, null, null)));
    }

    @Test
    public void generateOptionsFromRootTest() {
        PredefinedMenuOption option = new PredefinedMenuOption();
        ArrayList<MenuOptionInterface> childOptions = option.generateOptions(null, null, null);
        assertEquals("1: List Books.", childOptions.get(0).getTitle());
        assertEquals("2: Return Books.", childOptions.get(1).getTitle());
        assertEquals("3: List Movies.", childOptions.get(2).getTitle());
        assertEquals("4: Return Movies.", childOptions.get(3).getTitle());
    }

    @Test
    public void getOptionListBooksByUserInputTest() {
        PredefinedMenuOption option = new PredefinedMenuOption();
        option.generateOptions(null, null, null);
        assertEquals("Select a book to checkout.",
                option.getOptionByUserInput("1", option.generateOptions(null, null, null))
                        .getMessageBeforeUserInput());
    }

    @Test
    public void getOptionReturnBooksByUserInputTest() {
        PredefinedMenuOption option = new PredefinedMenuOption();
        option.generateOptions(null, null, null);
        assertEquals(ListItemsMenuOption.msgBooksToReturn,
                option.getOptionByUserInput("2", option.generateOptions(null, null, null))
                        .getMessageBeforeUserInput());
    }

    @Test
    public void getOptionListMoviesByUserInputTest() {
        PredefinedMenuOption option = new PredefinedMenuOption();
        option.generateOptions(null, null, null);
        assertEquals(ListItemsMenuOption.msgMoviesToCheckout,
                option.getOptionByUserInput("3", option.generateOptions(null, null, null))
                        .getMessageBeforeUserInput());
    }

    @Test
    public void getOptionReturnMoviesByUserInputTest() {
        PredefinedMenuOption option = new PredefinedMenuOption();
        option.generateOptions(null, null, null);
        assertEquals(ListItemsMenuOption.msgMoviesToReturn,
                option.getOptionByUserInput("4", option.generateOptions(null, null, null))
                        .getMessageBeforeUserInput());
    }
}
