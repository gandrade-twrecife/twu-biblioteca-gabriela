package com.twu.biblioteca;

import java.util.ArrayList;

public class MovieShelf implements BibliotecaItemShelf {

    public String checkoutItem(String userLogin, BibliotecaItem movie){
        return null;
    }
    public String returnItem(BibliotecaItem movie){
        return null;
    }
    public ArrayList<? extends BibliotecaItem> getAvailableItems(){
        return null;
    }
    public String formatItemToShowInList(BibliotecaItem item){
        return null;
    }
    public String getHeader(){
        return null;
    }
    public int getAmountOfItens() {
        return 0;
    }
    public ArrayList<? extends BibliotecaItem> getBorrowedItemsByUser(String userLogin) {
        return null;
    }
}
