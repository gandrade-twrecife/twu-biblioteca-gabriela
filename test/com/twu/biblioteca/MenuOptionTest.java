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
        MenuOption menu = MenuOption.createMenuRoot(library.rootMessage);
        assertEquals(library.rootMessage, menu.getMessageBeforeUserInput());
        assertTrue(menu.getParent() == null);
        assertEquals(1, menu.getChildOptions().size());
    }

    @Test
    public void createOptionTest() {
        MenuOption menuOption = MenuOption.createMenuOption(testOption, testTitle, testMessage);

        assertEquals(testOption, menuOption.getOption());
        assertEquals(testTitle, menuOption.getTitle());
        assertEquals(testMessage, menuOption.getMessageBeforeUserInput());
        assertTrue(menuOption.getParent() == null);
        assertEquals(0, menuOption.getChildOptions().size());
    }

    @Test
    public void addOptionToMenuTest() {
        MenuOption menu = MenuOption.createMenuRoot(library.rootMessage);

        MenuOption childOption = MenuOption.createMenuOption(testOption, testTitle, testMessage);
        menu.addOption(childOption);

        assertTrue(childOption.getParent() == menu);
        assertEquals(2, menu.getChildOptions().size());
    }

    @Test
    public void addOptionToOptionTest() {
        MenuOption menu = MenuOption.createMenuRoot(library.rootMessage);

        MenuOption childOption = MenuOption.createMenuOption(testOption, testTitle, testMessage);
        childOption.addOption(childOption);
        menu.addOption(childOption);
        childOption.addDefaultOptionsUsingParentInfo();

        assertTrue(childOption.getParent() == menu);
        assertEquals(3, childOption.getChildOptions().size());
        assertEquals(2, menu.getChildOptions().size());
    }

    @Test
    public void optionToStringTest() {
        MenuOption menu = MenuOption.createMenuRoot(library.rootMessage);

        MenuOption childOption1 = MenuOption.createMenuOption(testOption+"1", testTitle+"1", testMessage+"1");
        MenuOption childOption2 = MenuOption.createMenuOption(testOption+"2", testTitle+"2", testMessage+"2");
        childOption1.addOption(childOption2);
        childOption2.addDefaultOptionsUsingParentInfo();
        menu.addOption(childOption1);
        childOption1.addDefaultOptionsUsingParentInfo();

        String expected = testOption+"1" + testTitle+"1" + testMessage+"1" + menu.getMessageBeforeUserInput() +
                testOption+"2" + testTitle+"2" + "BackBack: Go back to previous menu.QuitQuit: Exit Biblioteca.";

        assertEquals(expected, childOption1.toString());
    }

    @Test
    public void addYesOptionGoingToMenuTest() {
        MenuOption menu = MenuOption.createMenuRoot(library.rootMessage);

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
        MenuOption menu = MenuOption.createMenuRoot(library.rootMessage);

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
        MenuOption menu = MenuOption.createMenuRoot(library.rootMessage);

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
        MenuOption menu = MenuOption.createMenuRoot(library.rootMessage);

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
        String expectedOption = "1";
        String expectedTitle = "1: Programming in Java                                                   Gabriela Andrade                                  2005";
        String expectedMessage = "Do you wish to checkout this book?";
        String expectedParentMessage = "The books available to check out are:\n" +
                "   Title                                                                 Author                                            Year\n" +
                "-------------------------------------------------------------------------------------------------------------------------------";
        String expectedChildren = "YY: Yes.NN: No.BackBack: Go back to previous menu.QuitQuit: Exit Biblioteca.";

        assertEquals(expectedOption + expectedTitle + expectedMessage + expectedParentMessage + expectedChildren,
                navigateToOption(library.menu, inputs).toString());
    }

    @Test
    public void navigatingOptionsUsingBackLastTest() {
        setUp();
        LinkedList<String> inputs = new LinkedList<String>(new LinkedList<String>(Arrays.asList("1", "1", "Back", "Back")));
        String expectedOption = "Back";
        String expectedTitle = "Back: Go back to previous menu.";
        String expectedMessage = "Type what is before the colon (:) to select the option.";
        String expectedParentMessage = "";
        String expectedChildren = "QuitQuit: Exit Biblioteca.11: List Books.";

        assertEquals(expectedOption + expectedTitle + expectedMessage + expectedParentMessage + expectedChildren,
                navigateToOption(library.menu, inputs).toString());
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
        library.setUpMenuOptions();
    }

    private void setUpBiblioteca() {
        library.books = new ArrayList<Book>();
        library.books.add(bookProgrammingInJava);
        library.books.add(bookProgrammingInCSharp);
        library.books.add(bookTDD);
    }
}
