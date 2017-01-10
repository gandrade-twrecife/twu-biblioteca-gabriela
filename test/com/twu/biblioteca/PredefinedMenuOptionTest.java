package com.twu.biblioteca;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
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
    public void createRootMenuTest() {
        PredefinedMenuOption rootMenu = PredefinedMenuOption.createMenuRoot("Root menu test");
        assertEquals("Root menu test", rootMenu.getMessageBeforeUserInput());
        assertTrue(rootMenu.getParent() == null);
        assertEquals(1, rootMenu.getChildOptions().size());
    }

    @Test
    public void setRootMenuTest() {
        PredefinedMenuOption menu = PredefinedMenuOption.createMenuRoot(library.rootMessage);
        assertEquals(library.rootMessage, menu.getMessageBeforeUserInput());
        assertTrue(menu.getParent() == null);
        assertEquals(1, menu.getChildOptions().size());
    }

    @Test
    public void createOptionTest() {
        PredefinedMenuOption predefinedMenuOption = PredefinedMenuOption.createMenuOption(testOption, testTitle, testMessage);

        assertEquals(testOption, predefinedMenuOption.getOption());
        assertEquals(testTitle, predefinedMenuOption.getTitle());
        assertEquals(testMessage, predefinedMenuOption.getMessageBeforeUserInput());
        assertTrue(predefinedMenuOption.getParent() == null);
        assertEquals(0, predefinedMenuOption.getChildOptions().size());
    }

    @Test
    public void addOptionToMenuTest() {
        PredefinedMenuOption menu = PredefinedMenuOption.createMenuRoot(library.rootMessage);

        PredefinedMenuOption childOption = PredefinedMenuOption.createMenuOption(testOption, testTitle, testMessage);
        menu.addOption(childOption);

        assertTrue(childOption.getParent() == menu);
        assertEquals(2, menu.getChildOptions().size());
    }

    @Test
    public void addOptionToOptionTest() {
        PredefinedMenuOption menu = PredefinedMenuOption.createMenuRoot(library.rootMessage);

        PredefinedMenuOption childOption = PredefinedMenuOption.createMenuOption(testOption, testTitle, testMessage);
        childOption.addOption(childOption);
        menu.addOption(childOption);
        childOption.addDefaultOptionsUsingParentInfo();

        assertTrue(childOption.getParent() == menu);
        assertEquals(3, childOption.getChildOptions().size());
        assertEquals(2, menu.getChildOptions().size());
    }

    @Test
    public void optionToStringTest() {
        PredefinedMenuOption menu = PredefinedMenuOption.createMenuRoot(library.rootMessage);

        PredefinedMenuOption childOption1 = PredefinedMenuOption.createMenuOption(testOption+"1", testTitle+"1", testMessage+"1");
        PredefinedMenuOption childOption2 = PredefinedMenuOption.createMenuOption(testOption+"2", testTitle+"2", testMessage+"2");
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
        PredefinedMenuOption menu = PredefinedMenuOption.createMenuRoot(library.rootMessage);

        String yesOptionReturnMessage = "Typed yes";

        PredefinedMenuOption childOption = PredefinedMenuOption.createMenuOption(testOption, testTitle, testMessage);
        childOption.addYesOption(menu, yesOptionReturnMessage);
        menu.addOption(childOption);
        String expected = "YY: Yes." + yesOptionReturnMessage + "\n" + menu.getMessageBeforeUserInput();
        for (int i = 0; i < menu.getChildOptions().size(); i++) {
            PredefinedMenuOption menuChild = menu.getChildOptions().get(i);
            expected += menuChild.getOption() + menuChild.getTitle();
        }

        assertEquals(expected, childOption.getOptionByUserInput("Y").toString());
    }

    @Test
    public void addYesOptionGoingToOtherOptionTest() {
        PredefinedMenuOption menu = PredefinedMenuOption.createMenuRoot(library.rootMessage);

        String yesOptionReturnMessage = "Typed yes";

        PredefinedMenuOption childOption1 = PredefinedMenuOption.createMenuOption(testOption+"1", testTitle+"1", testMessage+"1");
        PredefinedMenuOption childOption2 = PredefinedMenuOption.createMenuOption(testOption+"2", testTitle+"2", testMessage+"2");
        childOption1.addOption(childOption2);
        childOption2.addYesOption(childOption1, yesOptionReturnMessage);
        menu.addOption(childOption1);
        String expected = "YY: Yes." + yesOptionReturnMessage + "\n" + childOption1.getMessageBeforeUserInput();
        for (int i = 0; i < childOption1.getChildOptions().size(); i++) {
            PredefinedMenuOption menuChild = childOption1.getChildOptions().get(i);
            expected += menuChild.getOption() + menuChild.getTitle();
        }

        assertEquals(expected, childOption2.getOptionByUserInput("Y").toString());
    }

    @Test
    public void addNoOptionGoingToMenuTest() {
        PredefinedMenuOption menu = PredefinedMenuOption.createMenuRoot(library.rootMessage);

        PredefinedMenuOption childOption = PredefinedMenuOption.createMenuOption(testOption, testTitle, testMessage);
        childOption.addNoOption(menu);
        menu.addOption(childOption);
        String expected = "NN: No." + menu.getMessageBeforeUserInput();
        for (int i = 0; i < menu.getChildOptions().size(); i++) {
            PredefinedMenuOption menuChild = menu.getChildOptions().get(i);
            expected += menuChild.getOption() + menuChild.getTitle();
        }

        assertEquals(expected, childOption.getOptionByUserInput("N").toString());
    }

    @Test
    public void addNoOptionGoingToOtherOptionTest() {
        PredefinedMenuOption menu = PredefinedMenuOption.createMenuRoot(library.rootMessage);

        PredefinedMenuOption childOption1 = PredefinedMenuOption.createMenuOption(testOption+"1", testTitle+"1", testMessage+"1");
        PredefinedMenuOption childOption2 = PredefinedMenuOption.createMenuOption(testOption+"2", testTitle+"2", testMessage+"2");
        childOption1.addOption(childOption2);
        childOption2.addNoOption(childOption1);
        menu.addOption(childOption1);
        String expected = "NN: No." + childOption1.getMessageBeforeUserInput();
        for (int i = 0; i < childOption1.getChildOptions().size(); i++) {
            PredefinedMenuOption menuChild = childOption1.getChildOptions().get(i);
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
        String expectedMessage = "Do you wish to checkout this item?";
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
        String quitOption = "QuitQuit: Exit Biblioteca.";
        String listBooksOption = "11: List Books.";
        String returnBooksOption = "22: Return Books.";
        String listMoviesOption = "33: List Movies.";
        String returnMoviesOption = "44: Return Movies.";
        String expectedChildren = quitOption + listBooksOption + returnBooksOption + listMoviesOption + returnMoviesOption;

        assertEquals(expectedOption + expectedTitle + expectedMessage + expectedParentMessage + expectedChildren,
                navigateToOption(library.menu, inputs).toString());
    }

    private PredefinedMenuOption navigateToOption(PredefinedMenuOption initialOption, LinkedList<String> inputs) {
        if (inputs.size() == 0) return initialOption;
        else {
            String currentInput = inputs.pop();
            return navigateToOption(initialOption.getOptionByUserInput(currentInput), inputs);
        }
    }

    private void setUp() {
        setUpBiblioteca();
        library.user = new User("Gabriela Andrade", "gandrade", "asdf");
        library.setUpMenuOptions();
    }

    private void setUpBiblioteca() {
        library.bookShelf = new BookShelf();
        library.bookShelf.add(bookProgrammingInJava);
        library.bookShelf.add(bookProgrammingInCSharp);
        library.bookShelf.add(bookTDD);
    }
}
