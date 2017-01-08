package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MenuOption {
    private MenuOption parent;
    private ArrayList<MenuOption> childOptions = new ArrayList<MenuOption>();

    private String option;
    private String title;
    private String messageBeforeUserInput;

    public String getOption(){return option;}
    public String getTitle(){return title;}
    public String getMessageBeforeUserInput(){return messageBeforeUserInput;}
    public MenuOption getParent(){return parent;}
    public ArrayList<MenuOption> getChildOptions(){return childOptions;}

    private MenuOption (String option, String title, String messageBeforeUserInput) {
        this.option = option;
        this.title = title;
        this.messageBeforeUserInput = messageBeforeUserInput;
    }

    public static MenuOption createMenuOption(String option, String title, String messageBeforeUserInput) {
        return new MenuOption(option, title, messageBeforeUserInput);
    }

    public static MenuOption createMenuRoot(String messageBeforeUserInput) {
        MenuOption rootOption = new MenuOption(null, null, messageBeforeUserInput);
        rootOption.addOption(createQuitOption());
        return rootOption;
    }

    public static MenuOption createMenuOptionFromOtherOption(MenuOption otherOption) {
        return new MenuOption(otherOption.option, otherOption.title, otherOption.messageBeforeUserInput);
    }

    public void addYesOption(MenuOption optionToGo, String returnMessage) {
        String messageBeforeUserInput = returnMessage + "\n" + optionToGo.messageBeforeUserInput;
        MenuOption yesOption = new MenuOption("Y", "Y: Yes.", messageBeforeUserInput);
        yesOption.parent = optionToGo.parent;
        yesOption.childOptions = optionToGo.childOptions;
        this.childOptions.add(yesOption);
    }

    public void addNoOption(MenuOption optionToGo) {
        MenuOption noOption = new MenuOption("N", "N: No.", optionToGo.messageBeforeUserInput);
        noOption.parent = optionToGo.parent;
        noOption.childOptions = optionToGo.childOptions;
        this.childOptions.add(noOption);
    }

    private void addBackOption() {
        if (parent != null) {
            MenuOption backOption = new MenuOption("Back", "Back: Go back to previous menu.", parent.messageBeforeUserInput);
            backOption.parent = this.getParent().getParent();
            backOption.childOptions = parent.childOptions;
            childOptions.add(backOption);
        }
    }

    private static MenuOption createQuitOption() {
        return new MenuOption("Quit", "Quit: Exit Biblioteca.", null);
    }

    public void addOption(MenuOption option) {
        option.parent = this;
        childOptions.add(option);
    }

    public MenuOption getOptionByUserInput(String input) {
        for (int i = 0; i < childOptions.size(); i++) {
            MenuOption currentChild = childOptions.get(i);
            if (input.toLowerCase().equals(currentChild.option.toLowerCase())) {
                return currentChild;
            }
        }
        MenuOption optionNotFound = createMenuOptionFromOtherOption(parent);
        if (!optionNotFound.messageBeforeUserInput.contains("Select a valid option!"))
            optionNotFound.messageBeforeUserInput = "Select a valid option!\n" + optionNotFound.messageBeforeUserInput;
        return optionNotFound;
    }

    public String listOptions() {
        orderChildOptionsByTitle();

        String listOfOptions = "";
        if (childOptions.size() == 0) return "There are no options available.";
        for (int i = 0; i < childOptions.size(); i++) {
            if (i != 0) listOfOptions += "\n";
            listOfOptions += childOptions.get(i).title;
        }
        return listOfOptions;
    }

    private void orderChildOptionsByTitle() {
        Collections.sort(childOptions, new Comparator<MenuOption>() {
            @Override public int compare(MenuOption option1, MenuOption option2) {
                return option1.title.compareTo(option2.title);
            }
        });
    }

    public String toString() {
        String asString = "";
        if (option != null) asString += this.option;
        if (title != null) asString += this.title;
        if (messageBeforeUserInput != null) asString += this.messageBeforeUserInput;
        if (parent != null) asString += this.parent.messageBeforeUserInput;
        if (childOptions != null) {
            for (int i = 0; i < childOptions.size(); i++) {
                MenuOption child = childOptions.get(i);
                asString += child.option + child.title;
            }
        }

        return asString;
    }

    public void addDefaultOptionsUsingParentInfo() {
        this.addBackOption();
        this.addOption(createQuitOption());
    }

}
