package com.twu.biblioteca;

import java.util.ArrayList;

public class ConfirmationMenuOption implements MenuOptionInterface {
    private BibliotecaItem item;
    private String option;
    private String title;
    private String typeOfAction;
    private String userLogin;
    private MenuOptionInterface parent = new PredefinedMenuOption();
    public static String successfulBookCheckoutResponse = "Thank you! Enjoy the book.";
    public static String unsuccessfulBookCheckoutResponse = "That book is not available.";
    public static String successfulBookReturnResponse = "Thank you for returning the book.";
    public static String unsuccessfulBookReturnResponse = "That is not a valid book to return.";
    public static String successfulMovieCheckoutResponse = "Thank you! Enjoy the movie.";
    public static String unsuccessfulMovieCheckoutResponse = "That movie is not available.";
    public static String successfulMovieReturnResponse = "Thank you for returning the movie.";
    public static String unsuccessfulMovieReturnResponse = "That is not a valid movie to return.";
    public static String itemTypeError = "This item does not belong to this shelf.";

    public ConfirmationMenuOption(String option, String title, BibliotecaItem item, String typeOfAction) {
        this.item = item;
        this.option = option;
        this.title = title;
        this.typeOfAction = typeOfAction;
    }

    public MenuOptionInterface getParent() {
        return parent;
    }

    public ArrayList<MenuOptionInterface> generateOptions(BibliotecaItemShelf shelf, BibliotecaItem item, String userLogin) {
        this.userLogin = userLogin;
        return null;
    }

    public MenuOptionInterface getOptionByUserInput(String userInput, ArrayList<? extends MenuOptionInterface> options) {
        String returnMessage = "";
        if (typeOfAction.equals(ListItemsMenuOption.typeBooksToCheckout)) {
            returnMessage = checkoutBook(userLogin, item);
        }
        else if (typeOfAction.equals(ListItemsMenuOption.typeBooksToReturn)) {
            returnMessage = returnBook(item);
        }
        else if (typeOfAction.equals(ListItemsMenuOption.typeMoviesToCheckout)) {
            returnMessage = checkoutMovie(userLogin, item);
        }
        else if (typeOfAction.equals(ListItemsMenuOption.typeMoviesToReturn)) {
            returnMessage = returnMovie(item);
        }
        parent.setMessageBeforeUserInput(returnMessage + parent.getMessageBeforeUserInput());
        return getParent();
    }

    public String checkoutBook(String userLogin, BibliotecaItem book){
        if (book instanceof Book) {
            if (book.checkoutItem(userLogin)) return successfulBookCheckoutResponse;
            else return unsuccessfulBookCheckoutResponse;
        }
        return itemTypeError;
    }

    public String returnBook(BibliotecaItem book){
        if (book instanceof Book) {
            if (book.returnItem()) return successfulBookReturnResponse;
            else return unsuccessfulBookReturnResponse;
        }
        return itemTypeError;
    }

    public String checkoutMovie(String userLogin, BibliotecaItem movie){
        if (movie instanceof Movie) {
            if (movie.checkoutItem(userLogin)) return successfulMovieCheckoutResponse;
            else return unsuccessfulMovieCheckoutResponse;
        }
        return itemTypeError;
    }

    public String returnMovie(BibliotecaItem movie){
        if (movie instanceof Movie) {
            if (movie.returnItem()) return successfulMovieReturnResponse;
            else return unsuccessfulMovieReturnResponse;
        }
        return itemTypeError;
    }

    public String printOptions(ArrayList<? extends MenuOptionInterface> options) {return null;}
    public String getOption() {return null;}
    public String getTitle() {return null;}
    public String getMessageBeforeUserInput() {return null;}
    public void setMessageBeforeUserInput(String newMessage){}
}
