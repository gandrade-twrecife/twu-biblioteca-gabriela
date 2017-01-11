package com.twu.biblioteca;

import java.util.ArrayList;

public class PredefinedMenuOption implements MenuOptionInterface {
    private PredefinedMenuOption parent = null;

    private String option;
    private String title;
    public String messageBeforeUserInput;
    public static final String rootMessage = "Type the number or word before the colon (:) to select an option.\n";
    public static final String quitOption = "Quit";
    public static final String quitTitle = "Quit: Exit Biblioteca.";
    public static final String backOption = "Back";
    public static final String backTitle = "Back: Go back to previous menu.";

    public String getOption(){return option;}
    public String getTitle(){return title;}
    public String getMessageBeforeUserInput(){return messageBeforeUserInput;}
    public void setMessageBeforeUserInput(String newMessage){
        messageBeforeUserInput = newMessage;}

    public PredefinedMenuOption getParent(){return parent;}

    public static String invalidOptionMessage = "Select a valid option!";

    public ArrayList<MenuOptionInterface> generateOptions(BibliotecaItemShelf shelf, BibliotecaItem item, String userLogin) {
        ArrayList<MenuOptionInterface> childOptions = new ArrayList<MenuOptionInterface>();
        childOptions.add(new ListItemsMenuOption(ListItemsMenuOption.typeBooksToCheckout));
        childOptions.add(new ListItemsMenuOption(ListItemsMenuOption.typeBooksToReturn));
        childOptions.add(new ListItemsMenuOption(ListItemsMenuOption.typeMoviesToCheckout));
        childOptions.add(new ListItemsMenuOption(ListItemsMenuOption.typeMoviesToReturn));
        childOptions.add(createQuitOption());
        return childOptions;
    }

    public String printOptions(ArrayList<? extends MenuOptionInterface> options) {
        String textToReturn = PredefinedMenuOption.rootMessage;
        for (int i = 0; i < options.size(); i++) {
            if (i != 0) textToReturn += "\n";
            textToReturn += options.get(i).getTitle();
        }
        return textToReturn;
    }

    public MenuOptionInterface getOptionByUserInput(String userInput, ArrayList<? extends MenuOptionInterface> options) {
        for (int i = 0; i < options.size(); i++) {
            MenuOptionInterface option = options.get(i);
            if (userInput.toLowerCase().equals(option.getOption().toLowerCase()))
                return option;
        }
        if (!messageBeforeUserInput.contains(invalidOptionMessage))
            messageBeforeUserInput = invalidOptionMessage + "\n" + rootMessage;
        return this;
    }

    public PredefinedMenuOption(String option, String title, String messageBeforeUserInput) {
        this.option = option;
        this.title = title;
        this.messageBeforeUserInput = messageBeforeUserInput;
    }

    public PredefinedMenuOption() {
        messageBeforeUserInput = rootMessage;
    }

    public static PredefinedMenuOption createQuitOption() {
        return new PredefinedMenuOption(quitOption, quitTitle, null);
    }

    public static PredefinedMenuOption createBackOption() {
        return new PredefinedMenuOption(backOption, backTitle, null);
    }

}
