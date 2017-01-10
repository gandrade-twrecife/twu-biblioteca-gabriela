package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Scanner;

public class BibliotecaApp {

    public BookShelf bookShelf = new BookShelf();
    public MovieShelf movieShelf = new MovieShelf();
    public static String welcomeMessage = "Welcome to the Biblioteca!";
    public static String rootMessage = "Type what is before the colon (:) to select the option.";
    public User user;
    PredefinedMenuOption menu = PredefinedMenuOption.createMenuRoot(rootMessage);

    public BibliotecaApp(){}

    private PredefinedMenuOption setUpOptionToListBooks() {
        return PredefinedMenuOption.createMenuOption("1", "1: List Books.", bookShelf.getHeader());
    }

    private PredefinedMenuOption setUpOptionToReturnBooks() {
        return PredefinedMenuOption.createMenuOption("2", "2: Return Books.", bookShelf.getHeader());
    }

    private PredefinedMenuOption setUpOptionToListMovies() {
        return PredefinedMenuOption.createMenuOption("3", "3: List Movies.", movieShelf.getHeader());
    }

    private PredefinedMenuOption setUpOptionToReturnMovies() {
        return PredefinedMenuOption.createMenuOption("4", "4: Return Movies.", movieShelf.getHeader());
    }

    private void setUpOptionsInListItems(BibliotecaItemShelf shelf, PredefinedMenuOption listItemsOption,
                                         ArrayList<? extends BibliotecaItem> items) {
        int lengthOfItemIndexOptions = ((Integer)items.size()).toString().length();
        for (int i = 0; i < items.size(); i++) {
            String optionTitle = Utilities.formatNumbersEqualStringSize(i + 1, lengthOfItemIndexOptions) +
                    ": " + shelf.formatItemToShowInList(items.get(i));
            PredefinedMenuOption option = PredefinedMenuOption.createMenuOption(((Integer)(i + 1)).toString(), optionTitle, "Do you wish to checkout this item?");
            setUpYesNoOptions(option, items.get(i));
            listItemsOption.addOption(option);
            option.addDefaultOptionsUsingParentInfo();
        }
    }

    private void setUpYesNoOptions(PredefinedMenuOption option, BibliotecaItem item) {
        option.addYesOption(menu, bookShelf.checkoutItem(user.getLogin(), item));
        option.addNoOption(menu);
    }

    public void setUpMenuOptions() {
        PredefinedMenuOption[] options = new PredefinedMenuOption[4];
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
