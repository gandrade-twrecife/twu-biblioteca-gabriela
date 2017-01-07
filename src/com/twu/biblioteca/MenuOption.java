package com.twu.biblioteca;

import java.util.ArrayList;

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

    private MenuOption (String option, String title, String messageBeforeUserInput, boolean addDefaultOptions) {
        this.option = option;
        this.title = title;
        this.messageBeforeUserInput = messageBeforeUserInput;

        if (addDefaultOptions) addDefaultOptions();
    }

    public static MenuOption createMenuOption(String option, String title, String messageBeforeUserInput) {
        return new MenuOption(option, title, messageBeforeUserInput, true);
    }

    public static MenuOption createMenuRoot(String messageBeforeUserInput) {
        MenuOption rootOption = new MenuOption(null, null, messageBeforeUserInput, false);
        rootOption.addOption(createQuitOption());
        return rootOption;
    }

    public static MenuOption createMenuOptionFromOtherOption(MenuOption otherOption) {
        return new MenuOption(otherOption.option, otherOption.title, otherOption.messageBeforeUserInput, false);
    }

    public void addYesOption(MenuOption optionToGo, String returnMessage) {
        String messageBeforeUserInput = returnMessage + "\n" + optionToGo.messageBeforeUserInput;
        MenuOption yesOption = new MenuOption("Y", "Y: Yes.", messageBeforeUserInput, false);
        yesOption.parent = optionToGo.parent;
        yesOption.childOptions = optionToGo.childOptions;
        this.childOptions.add(yesOption);
    }

    public void addNoOption(MenuOption optionToGo) {
        MenuOption noOption = new MenuOption("N", "N: No.", optionToGo.messageBeforeUserInput, false);
        noOption.parent = optionToGo.parent;
        noOption.childOptions = optionToGo.childOptions;
        this.childOptions.add(noOption);
    }

    private MenuOption createBackOption() {
        MenuOption backOption = new MenuOption("Back", "Back: Go back to previous menu.", messageBeforeUserInput, false);
        backOption.parent = this;
        backOption.childOptions = childOptions;

        return backOption;
    }

    private static MenuOption createQuitOption() {
        return new MenuOption("Quit", "Quit: Exit Biblioteca.", null, false);
    }

    public void addOption(MenuOption option) {
        option.parent = this;
        childOptions.add(option);
    }

    public MenuOption getOptionByUserInput(String input) {
        for (int i = 0; i < childOptions.size(); i++) {
            MenuOption currentChild = childOptions.get(i);
            if (input.equals(currentChild.option)) {
                return currentChild;
            }
        }
        MenuOption optionNotFound = createMenuOptionFromOtherOption(parent);
        optionNotFound.messageBeforeUserInput = "Select a valid option!\n" + optionNotFound.messageBeforeUserInput;
        return optionNotFound;
    }

    public String listOptions() {
        String listOfOptions = "";
        if (childOptions.size() == 0) return "There are no options available.";
        for (int i = 0; i < childOptions.size(); i++) {
            if (i != 0) listOfOptions += "\n";
            listOfOptions += childOptions.get(i).title;
        }
        return listOfOptions;
    }

    public boolean equals(MenuOption optionToCompare) {
        if (optionToCompare == null) return false;

        boolean equalOption = (this.option != null & optionToCompare.option != null);
        if (equalOption) equalOption = optionToCompare.option.equals(this.option);

        boolean equalTitles = (this.title != null & optionToCompare.title != null);
        if (equalTitles) equalTitles = optionToCompare.title.equals(this.title);

        boolean equalMessage = (this.messageBeforeUserInput != null & optionToCompare.messageBeforeUserInput != null);
        if (equalMessage) equalMessage = optionToCompare.messageBeforeUserInput.equals(this.messageBeforeUserInput);

        boolean equalParent = (this.parent != null & optionToCompare.parent != null);
        if (equalParent) equalParent = optionToCompare.parent.equals(this.parent);

        boolean equalChildren = (this.childOptions != null & optionToCompare.childOptions != null);
        if (equalChildren) equalChildren = optionToCompare.childOptions.equals(this.childOptions);

        if (equalOption & equalTitles & equalMessage & equalParent & equalChildren)
            return true;
        else
            return false;
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

    private void addDefaultOptions() {
        this.addOption(createBackOption());
        this.addOption(createQuitOption());
    }

}
