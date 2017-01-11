package com.twu.biblioteca;

import java.util.ArrayList;

public class ItemMenuOption implements MenuOptionInterface {
    private String typeOfItem;
    private BibliotecaItem item;
    private String option;
    private String title;
    private String messageBeforeUserInput;

    public ItemMenuOption(String option, String title, BibliotecaItem item, String typeOfItem) {
        this.typeOfItem = typeOfItem;
        this.option = option;
        this.title = title;
        this.item = item;

        if (typeOfItem.equals(ListItemsMenuOption.typeBooksToCheckout))
            messageBeforeUserInput = "Do you wish to checkout this book?";
        else if (typeOfItem.equals(ListItemsMenuOption.typeBooksToReturn))
            messageBeforeUserInput = "Do you wish to return this book?";
        else if (typeOfItem.equals(ListItemsMenuOption.typeMoviesToCheckout))
            messageBeforeUserInput = "Do you wish to checkout this movie?";
        else if (typeOfItem.equals(ListItemsMenuOption.typeMoviesToReturn))
            messageBeforeUserInput = "Do you wish to return this movie?";
    }

    public MenuOptionInterface getParent() {
        return new ListItemsMenuOption(typeOfItem);
    }

    public ArrayList<MenuOptionInterface> generateOptions(BibliotecaItemShelf shelf, BibliotecaItem item, String userLogin) {
        ArrayList<MenuOptionInterface> returnOptions = new ArrayList<MenuOptionInterface>();
        returnOptions.add(new ConfirmationMenuOption("Y", "Y: Yes.", item, typeOfItem));
        returnOptions.add(new ConfirmationMenuOption("N", "N: No.", null, typeOfItem));
        returnOptions.add(new PredefinedMenuOption("Back", "Back: Go back to previous menu.", null));
        return returnOptions;
    }

    public String printOptions(ArrayList<? extends MenuOptionInterface> options) {
        String textToReturn = messageBeforeUserInput + "\n";
        for (int i = 0; i < options.size(); i++) {
            if (i!=0) textToReturn += "\n";
            textToReturn += options.get(i).getTitle();
        }
        return textToReturn;
    }

    public MenuOptionInterface getOptionByUserInput(String userInput, ArrayList<? extends MenuOptionInterface> options) {
        for (int i = 0; i < options.size(); i++) {
            if (userInput.toLowerCase().equals(options.get(i).getOption())) return options.get(i);
            else if (userInput.toLowerCase().equals("back")) return getParent();
        }
        if (!messageBeforeUserInput.contains(PredefinedMenuOption.invalidOptionMessage))
            messageBeforeUserInput = PredefinedMenuOption.invalidOptionMessage + messageBeforeUserInput;
        return this;
    }

    @Override
    public String getOption() {
        return option;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getMessageBeforeUserInput() {
        return null;
    }

    public void setMessageBeforeUserInput(String newMessage) {
        messageBeforeUserInput = newMessage;
    }
}
