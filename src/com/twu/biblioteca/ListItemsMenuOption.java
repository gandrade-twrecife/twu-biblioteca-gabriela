package com.twu.biblioteca;

import java.util.ArrayList;

public class ListItemsMenuOption implements MenuOptionInterface {
    private String typeOfList;
    private PredefinedMenuOption parent = new PredefinedMenuOption();
    private String messageBeforeUserInput;
    private String option;
    private String title;

    public static String typeBooksToCheckout = "books to checkout";
    public static String typeBooksToReturn = "books to return";
    public static String typeMoviesToCheckout = "movies to checkout";
    public static String typeMoviesToReturn = "movies to return";
    public static final String msgBooksToCheckout = "Select a book to checkout.";
    public static final String msgBooksToReturn = "Select a book to return it.";
    public static final String msgMoviesToCheckout = "Select a movie to checkout.";
    public static final String msgMoviesToReturn = "Select a movie to return it.";

    public ListItemsMenuOption(String typeOfList) {
        this.typeOfList = typeOfList;
        if (typeOfList.equals(typeBooksToCheckout)) {
            option = "1";
            title = "1: List Books.";
            messageBeforeUserInput = msgBooksToCheckout;
        }
        if (typeOfList.equals(typeBooksToReturn)) {
            option = "2";
            title = "2: Return Books.";
            messageBeforeUserInput = msgBooksToReturn;
        }
        if (typeOfList.equals(typeMoviesToCheckout)) {
            option = "3";
            title = "3: List Movies.";
            messageBeforeUserInput = msgMoviesToCheckout;
        }
        if (typeOfList.equals(typeMoviesToReturn)) {
            option = "4";
            title = "4: Return Movies.";
            messageBeforeUserInput = msgMoviesToReturn;
        }
    }

    public String getTypeOfList() {return typeOfList;}

    public MenuOptionInterface getParent() {
        return parent;
    }

    public ArrayList<MenuOptionInterface> generateOptionsByList(BibliotecaItemShelf shelf, ArrayList<? extends BibliotecaItem> items) {
        ArrayList<MenuOptionInterface> childOptions = new ArrayList<MenuOptionInterface>();
        for (int i = 0; i < items.size(); i++) {
            BibliotecaItem item = items.get(i);
            int sizeOfIndex = ((Integer)items.size()).toString().length();
            childOptions.add(new ItemMenuOption(((Integer)(i+1)).toString(),
                    Utilities.formatNumbersEqualStringSize(i+1, sizeOfIndex)
                            + ": " + Utilities.formatItemToShowInList(item), item, typeOfList));
        }
        childOptions.add(PredefinedMenuOption.createBackOption());
        return childOptions;
    }

    public ArrayList<MenuOptionInterface> generateOptions(BibliotecaItemShelf shelf, BibliotecaItem item, String userLogin) {
        if (typeOfList.equals(typeBooksToCheckout)) {
            ArrayList<Book> booksToCheckout = (ArrayList<Book>)shelf.getAvailableItems();
            return generateOptionsByList(shelf, booksToCheckout);
        }
        else if (typeOfList.equals(typeBooksToReturn)) {
            ArrayList<Book> booksToReturn = (ArrayList<Book>)shelf.getBorrowedItemsByUser(userLogin);
            return generateOptionsByList(shelf, booksToReturn);
        }
        else if (typeOfList.equals(typeMoviesToCheckout)) {
            ArrayList<Movie> moviesToCheckout = (ArrayList<Movie>)shelf.getAvailableItems();
            return generateOptionsByList(shelf, moviesToCheckout);
        }
        else if (typeOfList.equals(typeMoviesToReturn)) {
            ArrayList<Movie> moviesToReturn = (ArrayList<Movie>)shelf.getBorrowedItemsByUser(userLogin);
            return generateOptionsByList(shelf, moviesToReturn);
        }
        return null;
    }

    public String printOptions(ArrayList<? extends MenuOptionInterface> options) {
        String textToReturn = getMessageBeforeUserInput() + "\n";
        for (int i = 0; i < options.size(); i++) {
            if (i!=0) textToReturn += "\n";
            textToReturn += options.get(i).getTitle();
        }
        return textToReturn;
    }

    public MenuOptionInterface getOptionByUserInput(String userInput, ArrayList<? extends MenuOptionInterface> options) {
        for (int i = 0; i < options.size(); i++) {
            if (options.get(i).getOption().toLowerCase().equals(userInput.toLowerCase())) return options.get(i);
            else if (userInput.toLowerCase().equals("back")) return getParent();
        }
        if (!this.getMessageBeforeUserInput().contains(PredefinedMenuOption.invalidOptionMessage))
            messageBeforeUserInput = PredefinedMenuOption.invalidOptionMessage + "\n" + getMessageBeforeUserInput();
        return this;
    }

    public String getOption() {
        return this.option;
    }

    public String getTitle() {
        return this.title;
    }

    public String getMessageBeforeUserInput() {
        return messageBeforeUserInput;
    }

    public void setMessageBeforeUserInput(String newMessage) {
        messageBeforeUserInput = newMessage;
    }
}
