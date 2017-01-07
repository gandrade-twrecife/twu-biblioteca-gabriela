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

    @Test
    public void addNoOptionGoingToMenuTest() {
        setUpRootMenuOption();

        MenuOption childOption = MenuOption.createMenuOption(testOption, testTitle, testMessage);
        childOption.addNoOption(menu);
        menu.addOption(childOption);
        String expected = "NN: No." + menu.getMessageBeforeUserInput();
        for (int i = 0; i < menu.getChildOptions().size(); i++) {
            MenuOption menuChild = menu.getChildOptions().get(i);
            expected += menuChild.getOption() + menuChild.getTitle();
        }

        assertEquals(expected, childOption.getOptionByUserInput("N").toString());
    }

    @Test
    public void addNoOptionGoingToOtherOptionTest() {
        setUpRootMenuOption();

        MenuOption childOption1 = MenuOption.createMenuOption(testOption+"1", testTitle+"1", testMessage+"1");
        MenuOption childOption2 = MenuOption.createMenuOption(testOption+"2", testTitle+"2", testMessage+"2");
        childOption1.addOption(childOption2);
        childOption2.addNoOption(childOption1);
        menu.addOption(childOption1);
        String expected = "NN: No." + childOption1.getMessageBeforeUserInput();
        for (int i = 0; i < childOption1.getChildOptions().size(); i++) {
            MenuOption menuChild = childOption1.getChildOptions().get(i);
            expected += menuChild.getOption() + menuChild.getTitle();
        }

        assertEquals(expected, childOption2.getOptionByUserInput("N").toString());
    }

    @Test
    public void navigatingOptionsGoingOnlyDownTest() {
        setUp();
        LinkedList<String> inputs = new LinkedList<String>(new LinkedList<String>(Arrays.asList("1","1")));

        System.out.println(navigateToOption(menu, inputs));
    }

    @Test
    public void navigatingOptionsUsingBackLastTest() {
        setUp();
        LinkedList<String> inputs = new LinkedList<String>(new LinkedList<String>(Arrays.asList("1","1")));
        String expectedOption = "1";
        String expectedTitle = "1: Programming in Java                                                   Gabriela Andrade                                  2005";
        String expectedMessage = "Do you wish to checkout this book?";
        String expectedParentMessage = "The books available to check out are:\n" +
                "   Title                                                                 Author                                            Year\n" +
                "-------------------------------------------------------------------------------------------------------------------------------";
        String expectedChildren = "BackBack: Go back to previous menu.QuitQuit: Exit Biblioteca.YY: Yes.NN: No.";

        assertEquals(expectedOption + expectedTitle + expectedMessage + expectedParentMessage + expectedChildren,
                navigateToOption(menu, inputs).toString());
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
        MenuOption listBooksOption = setUpOptionToListBooks();
        menu.addOption(listBooksOption);
        setUpOptionsInListOfBooks(listBooksOption, library.books);
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

    private MenuOption setUpOptionToListBooks() {
        int amountOfBooks = library.books.size();
        String listBooksOptionMessage = "There are no available books to checkout.";
        if (amountOfBooks > 0) {
            int spacesToTheLeftOfHeaders = ((Integer)amountOfBooks).toString().length() + 2;
            String columnsHeader = library.addCharsToTheRight("", spacesToTheLeftOfHeaders, ' ')
                    + "Title";
            columnsHeader = BibliotecaApp.addCharsToTheRight(columnsHeader, library.sizeOfBookTitles - 5, ' ');
            columnsHeader += "Author";
            columnsHeader = BibliotecaApp.addCharsToTheRight(columnsHeader, library.sizeOfBookAuthors - 6, ' ');
            columnsHeader += "Year\n";
            columnsHeader = BibliotecaApp.addCharsToTheRight(columnsHeader, spacesToTheLeftOfHeaders + library.sizeOfBookTitles + library.sizeOfBookAuthors + 4, '-');

            listBooksOptionMessage = "The books available to check out are:\n" + columnsHeader;
        }
        return MenuOption.createMenuOption("1", "1: List Books.", listBooksOptionMessage);
    }

    private void setUpOptionsInListOfBooks(MenuOption listBooksOption, ArrayList<Book> books) {
        int lengthOfBookIndexOptions = ((Integer)books.size()).toString().length();
        for (int i = 0; i < books.size(); i++) {
            String bookOptionTitle = BibliotecaApp.formatNumbersEqualStringSize(i + 1, lengthOfBookIndexOptions) +
                    ": " + BibliotecaApp.showFormattedBook(books.get(i));
            MenuOption bookOption = MenuOption.createMenuOption(((Integer)(i + 1)).toString(), bookOptionTitle, "Do you wish to checkout this book?");
            setUpBookOptions(bookOption, books.get(i));
            listBooksOption.addOption(bookOption);
        }
    }

    private void setUpBookOptions(MenuOption bookOption, Book book) {
        bookOption.addYesOption(menu, library.checkoutBook(book));
        bookOption.addNoOption(menu);
    }
}
