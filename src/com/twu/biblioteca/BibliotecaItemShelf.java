package com.twu.biblioteca;

import java.util.ArrayList;

public interface BibliotecaItemShelf {
    public String checkoutItem(String userLogin, BibliotecaItem item);
    public String returnItem(BibliotecaItem item);
    public ArrayList<? extends BibliotecaItem> getAvailableItems();
    public ArrayList<? extends BibliotecaItem> getBorrowedItemsByUser(String userLogin);
    public String formatItemToShowInList(BibliotecaItem item);
    public String getHeader();
    public int getAmountOfItens();
    public void add(BibliotecaItem item);
}
