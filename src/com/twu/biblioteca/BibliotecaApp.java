package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Scanner;

public class BibliotecaApp {

    public BookShelf bookShelf = new BookShelf();
    public MovieShelf movieShelf = new MovieShelf();
    public static String welcomeMessage = "Welcome to the Biblioteca!";
    public static String rootMessage = "Type what is before the colon (:) to select the option.";
    public User user;
    MenuOption menu = MenuOption.createMenuRoot(rootMessage);

    public BibliotecaApp(){}

    private MenuOption setUpOptionToListBooks() {
        return MenuOption.createMenuOption("1", "1: List Books.", bookShelf.getHeader());
    }

    private MenuOption setUpOptionToReturnBooks() {
        return MenuOption.createMenuOption("2", "2: Return Books.", bookShelf.getHeader());
    }

    private MenuOption setUpOptionToListMovies() {
        return MenuOption.createMenuOption("3", "3: List Movies.", movieShelf.getHeader());
    }

    private MenuOption setUpOptionToReturnMovies() {
        return MenuOption.createMenuOption("4", "4: Return Movies.", movieShelf.getHeader());
    }

    private void setUpOptionsInListItems(BibliotecaItemShelf shelf, MenuOption listItemsOption,
                                         ArrayList<? extends BibliotecaItem> items) {
        int lengthOfItemIndexOptions = ((Integer)items.size()).toString().length();
        for (int i = 0; i < items.size(); i++) {
            String optionTitle = Utilities.formatNumbersEqualStringSize(i + 1, lengthOfItemIndexOptions) +
                    ": " + shelf.formatItemToShowInList(items.get(i));
            MenuOption option = MenuOption.createMenuOption(((Integer)(i + 1)).toString(), optionTitle, "Do you wish to checkout this item?");
            setUpYesNoOptions(option, items.get(i));
            listItemsOption.addOption(option);
            option.addDefaultOptionsUsingParentInfo();
        }
    }

    private void setUpYesNoOptions(MenuOption option, BibliotecaItem item) {
        option.addYesOption(menu, bookShelf.checkoutItem(user.getLogin(), item));
        option.addNoOption(menu);
    }

    public void setUpMenuOptions() {
        MenuOption[] options = new MenuOption[4];
        options[0] = setUpOptionToListBooks();
        options[1] = setUpOptionToReturnBooks();
        options[2] = setUpOptionToListMovies();
        options[3] = setUpOptionToReturnMovies();
        for (int i = 0; i < options.length; i++) {
            menu.addOption(options[i]);
            options[i].addDefaultOptionsUsingParentInfo();
        }
        setUpOptionsInListItems(bookShelf, options[0], bookShelf.getAvailableItems());
        setUpOptionsInListItems(bookShelf, options[1], bookShelf.getBorrowedItemsByUser(user.getLogin()));
        setUpOptionsInListItems(movieShelf, options[2], movieShelf.getAvailableItems());
        setUpOptionsInListItems(movieShelf, options[3], movieShelf.getBorrowedItemsByUser(user.getLogin()));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BibliotecaApp app = new BibliotecaApp();
        app.setUpMenuOptions();

        System.out.println(app.welcomeMessage);

        do {
            System.out.println(app.menu.getMessageBeforeUserInput());
            System.out.println(app.menu.listOptions());

            String userInput = scanner.next();
            if (userInput.toLowerCase().equals("quit")) break;

            app.menu = app.menu.getOptionByUserInput(userInput);
        } while(true);

    }
}
