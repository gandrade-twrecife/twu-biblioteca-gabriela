package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class PredefinedMenuOption implements MenuOptionInterface {
    private PredefinedMenuOption parent = null;

    private String option;
    private String title;
    private String messageBeforeUserInput;

    public String getOption(){return option;}
    public String getTitle(){return title;}
    public String getMessageBeforeUserInput(){return messageBeforeUserInput;}
    public PredefinedMenuOption getParent(){return parent;}

    public static String invalidOptionMessage = "Select a valid option!";

    public ArrayList<MenuOptionInterface> generateOptions() {
        ArrayList<MenuOptionInterface> childOptions = new ArrayList<MenuOptionInterface>();
        //childOptions.add(new ListOfItemsMenuOption("1", "1: List Books."));
        //childOptions.add(new ListOfItemsMenuOption("2", "2: Return Books."));
        //childOptions.add(new ListOfItemsMenuOption("3", "3: List Movies."));
        //childOptions.add(new ListOfItemsMenuOption("4", "4: Return Movies."));
        childOptions.add(new PredefinedMenuOption("Quit", "Quit: Exit Biblioteca.", ""));
        return childOptions;
    }

    public String printOptions(ArrayList<? extends MenuOptionInterface> options) {
        String textToReturn = "Type the number or word before the colon (:) to select an option.\n";
        for (int i = 0; i < options.size(); i++) {
            if (i != 0) textToReturn += "\n";
            textToReturn += options.get(i).getTitle();
        }
        return textToReturn;
    }

    @Override
    public MenuOptionInterface getOptionByUserInput(String userInput, ArrayList<? extends MenuOptionInterface> options) {
        for (int i = 0; i < options.size(); i++) {
            MenuOptionInterface option = options.get(i);
            if (userInput.toLowerCase().equals(option.getOption().toLowerCase()))
                return option;
        }
        if (!messageBeforeUserInput.contains(invalidOptionMessage))
            messageBeforeUserInput = invalidOptionMessage + "\n" + messageBeforeUserInput;
        return this;
    }

    public PredefinedMenuOption(String option, String title, String messageBeforeUserInput) {
        this.option = option;
        this.title = title;
        this.messageBeforeUserInput = messageBeforeUserInput;
    }

    public static PredefinedMenuOption createQuitOption() {
        return new PredefinedMenuOption("Quit", "Quit: Exit Biblioteca.", null);
    }

}
