package com.twu.biblioteca;

import java.util.ArrayList;

public interface BibliotecaItemShelf {
    public String checkoutItem();
    public String returnItem();
    public ArrayList<? extends BibliotecaItem> getAvailableItems();
    public String formatItemToShowInList();
    public String getHeader();

}
