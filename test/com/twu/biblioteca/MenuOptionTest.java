package com.twu.biblioteca;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class MenuOptionTest {
    MenuOption menu;
    BibliotecaApp library = new BibliotecaApp();
    Book bookProgrammingInJava = new Book("Programming in Java", "Gabriela Andrade", 2005);
    Book bookProgrammingInCSharp = new Book("Programming in C#", "Gabriela Andrade", 2006);
    Book bookTDD = new Book("Test Driven Development", "Gabriela Andrade", 2007);
    MenuOption yes;
    MenuOption no;
    String testOption = "Test option";
    String testTitle = "Test title";
    String testMessage = "Test message";


    @Test
    public void getRightOptionFromRootTest() {
        setUp();
        MenuOption targetOption = yes;
        LinkedList<String> inputs = new LinkedList<String>(Arrays.asList("1","1"));
        MenuOption actualOption = navigateToOption(targetOption, inputs);
        assertTrue(targetOption.equals(actualOption));
    }

    @Test
    public void createRootMenuTest() {
        MenuOption rootMenu = MenuOption.createMenuRoot("Root menu test");
        assertEquals("Root menu test", rootMenu.getMessageBeforeUserInput());
        assertTrue(rootMenu.getParent() == null);
        assertEquals(1, rootMenu.getChildOptions().size());
    }

    @Test
    public void setRootMenuTest() {
        setUpRootMenuOption();
        assertEquals(library.rootMessage, menu.getMessageBeforeUserInput());
        assertTrue(menu.getParent() == null);
        assertEquals(1, menu.getChildOptions().size());
    }

    @Test
    public void createOptionTest() {
        MenuOption menu = MenuOption.createMenuOption(testOption, testTitle, testMessage);

        assertEquals(testOption, menu.getOption());
        assertEquals(testTitle, menu.getTitle());
        assertEquals(testMessage, menu.getMessageBeforeUserInput());
        assertTrue(menu.getParent() == null);
        assertEquals(2, menu.getChildOptions().size());
    }

    @Test
    public void addOptionToMenuTest() {
        setUpRootMenuOption();

        MenuOption childOption = MenuOption.createMenuOption(testOption, testTitle, testMessage);
        menu.addOption(childOption);

        assertTrue(childOption.getParent() == menu);
        assertEquals(2, menu.getChildOptions().size());
    }

    @Test
    public void addOptionToOptionTest() {
        setUpRootMenuOption();

        MenuOption childOption = MenuOption.createMenuOption(testOption, testTitle, testMessage);
        childOption.addOption(childOption);
        menu.addOption(childOption);

        assertTrue(childOption.getParent() == menu);
        assertEquals(3, childOption.getChildOptions().size());
        assertEquals(2, menu.getChildOptions().size());
    }

    @Test
    public void optionToStringTest() {
        setUpRootMenuOption();

        MenuOption childOption = MenuOption.createMenuOption(testOption, testTitle, testMessage);
        childOption.addOption(childOption);
        menu.addOption(childOption);

        String expected = testOption + testTitle + testMessage + menu.getMessageBeforeUserInput() +
                "BackBack: Go back to previous menu.QuitQuit: Exit Biblioteca." +
                testOption + testTitle;

        assertEquals(expected, childOption.toString());
    }

    @Test
    public void addYesOptionGoingToMenuTest() {
        setUpRootMenuOption();

        String yesOptionReturnMessage = "Typed yes";

        MenuOption childOption = MenuOption.createMenuOption(testOption, testTitle, testMessage);
        childOption.addYesOption(menu, yesOptionReturnMessage);
        menu.addOption(childOption);
        String expected = "YY: Yes." + yesOptionReturnMessage + "\n" + menu.getMessageBeforeUserInput();
        for (int i = 0; i < menu.getChildOptions().size(); i++) {
            MenuOption menuChild = menu.getChildOptions().get(i);
            expected += menuChild.getOption() + menuChild.getTitle();
        }

        assertEquals(expected, childOption.getOptionByUserInput("Y").toString());
    }

    @Test
    public void addYesOptionGoingToOtherOptionTest() {
        setUpRootMenuOption();

        String yesOptionReturnMessage = "Typed yes";

        MenuOption childOption1 = MenuOption.createMenuOption(testOption+"1", testTitle+"1", testMessage+"1");
        MenuOption childOption2 = MenuOption.createMenuOption(testOption+"2", testTitle+"2", testMessage+"2");
        childOption1.addOption(childOption2);
        childOption2.addYesOption(childOption1, yesOptionReturnMessage);
        menu.addOption(childOption1);
        String expected = "YY: Yes." + yesOptionReturnMessage + "\n" + childOption1.getMessageBeforeUserInput();
        for (int i = 0; i < childOption1.getChildOptions().size(); i++) {
            MenuOption menuChild = childOption1.getChildOptions().get(i);
            expected += menuChild.getOption() + menuChild.getTitle();
        }

        assertEquals(expected, childOption2.getOptionByUserInput("Y").toString());
    }

    private MenuOption navigateToOption(MenuOption initialOption, LinkedList<String> inputs) {
        if (inputs.size() == 0) return initialOption;
        else {
            String currentInput = inputs.pop();
            return navigateToOption(initialOption.getOptionByUserInput(currentInput), inputs);
        }
    }

    private void setUp() {
        setUpBiblioteca();
        setUpRootMenuOption();
        MenuOption listBooksOption = MenuOption.createMenuOption("1", "1: List Books.", "The books available to check out are:");
        menu.addOption(listBooksOption);
        setUpOptionsInListOfBooks(listBooksOption, library);
    }

    private void setUpRootMenuOption() {
        menu = MenuOption.createMenuRoot(library.rootMessage);
    }

    private void setUpBiblioteca() {
        library.books = new ArrayList<Book>();
        library.books.add(bookProgrammingInJava);
        library.books.add(bookProgrammingInCSharp);
        library.books.add(bookTDD);
    }

    private void setUpOptionsInListOfBooks(MenuOption listBooksOption, BibliotecaApp library) {
        ArrayList<Book> books = library.books;
        int lengthOfBookIndexOptions = ((Integer)books.size()).toString().length();
        for (int i = 0; i < books.size(); i++) {
            String bookOptionTitle = BibliotecaApp.formatNumbersEqualStringSize(i + 1, lengthOfBookIndexOptions) +
                    BibliotecaApp.showFormattedBook(books.get(i));
            MenuOption bookOption = MenuOption.createMenuOption((i + 1) + "", bookOptionTitle, "Do you wish to checkout this book?");
            setUpBookOptions(bookOption, books.get(i));
            listBooksOption.addOption(bookOption);
        }
    }

    private void setUpBookOptions(MenuOption bookOption, Book book) {
        bookOption.addYesOption(menu, library.checkoutBook(book));
        bookOption.addNoOption(menu);
    }
}
