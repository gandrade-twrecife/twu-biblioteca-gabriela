package com.twu.biblioteca;

public interface BibliotecaItem {
    public boolean returnItem();
    public boolean checkoutItem(String userLogin);
    public boolean isBorrowed();
}
