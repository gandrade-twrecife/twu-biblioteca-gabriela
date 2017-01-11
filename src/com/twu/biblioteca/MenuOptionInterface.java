package com.twu.biblioteca;

import java.util.ArrayList;

public interface MenuOptionInterface {
    public MenuOptionInterface getParent();
    public ArrayList<MenuOptionInterface> generateOptions(BibliotecaItemShelf shelf, BibliotecaItem item, String userLogin);
    public String printOptions(ArrayList< ? extends MenuOptionInterface> options);
    public MenuOptionInterface getOptionByUserInput(String userInput, ArrayList< ? extends MenuOptionInterface> options);
    public String getOption();
    public String getTitle();
    public String getMessageBeforeUserInput();
    public void setMessageBeforeUserInput(String newMessage);
}
