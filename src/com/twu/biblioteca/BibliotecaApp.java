package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Scanner;

public class BibliotecaApp {

    //public ArrayList<Book> books = new ArrayList<Book>();
    public BookShelf bookShelf = new BookShelf();
    public static String welcomeMessage = "Welcome to the Biblioteca!";
    public static String rootMessage = "Type what is before the colon (:) to select the option.";
    public static String listBooksOptionMessage = "There are no available books to checkout.";
    private User user = new User("Gabriela Andrade", "gandrade", "asdf");
    private static int sizeOfBookTitles = 70;
    private static int sizeOfBookAuthors = 50;
    MenuOption menu = MenuOption.createMenuRoot(rootMessage);

    public BibliotecaApp(){}

    //OK
    public String checkoutBook(Book book) {
        if (book.checkoutItem(user.getLogin())) {
            return "Thank you! Enjoy the book.";
        }
        else {
            return "That book is not available.";
        }
    }

    //OK
    public String returnBook(Book book) {
        if (book.returnItem()) {
            return "Thank you for returning the book.";
        }
        else {
            return "That is not a valid book to return.";
        }
    }
    /*
    //OK
    public ArrayList<Book> getAvailableBooks() {
        ArrayList<Book> notBorrowedBooks = new ArrayList<Book>();
        if (books != null) {
            for (int i = 0; i < books.size(); i++) {
                if (!books.get(i).isBorrowed()) {
                    notBorrowedBooks.add(books.get(i));
                }
            }
        }
        return notBorrowedBooks;
    }*/

    public static String showFormattedBook(Book book) {
        int sizeTitle = sizeOfBookTitles;
        int sizeAuthor = sizeOfBookAuthors;

        int spacesToAdd = (sizeTitle - book.getTitle().length());
        String formattedBook = Utilities.addCharsToTheRight(book.getTitle(), spacesToAdd, ' ');

        spacesToAdd = (sizeAuthor - book.getAuthor().length());
        formattedBook += Utilities.addCharsToTheRight(book.getAuthor(), spacesToAdd, ' ');

        formattedBook += book.getYearPublished();

        return formattedBook;
    }

    private MenuOption setUpOptionToListBooks() {
        ArrayList<Book> books = (ArrayList<Book>)bookShelf.getAvailableItems();
        int amountOfBooks = books.size();
        if (amountOfBooks > 0) {
            int spacesToTheLeftOfHeaders = ((Integer)amountOfBooks).toString().length() + 2;
            String columnsHeader = Utilities.addCharsToTheRight("", spacesToTheLeftOfHeaders, ' ')
                    + "Title";
            columnsHeader = Utilities.addCharsToTheRight(columnsHeader, sizeOfBookTitles - 5, ' ');
            columnsHeader += "Author";
            columnsHeader = Utilities.addCharsToTheRight(columnsHeader, sizeOfBookAuthors - 6, ' ');
            columnsHeader += "Year\n";
            columnsHeader = Utilities.addCharsToTheRight(columnsHeader, spacesToTheLeftOfHeaders + sizeOfBookTitles + sizeOfBookAuthors + 4, '-');

            listBooksOptionMessage = "The books available to check out are:\n" + columnsHeader;
        }
        return MenuOption.createMenuOption("1", "1: List Books.", listBooksOptionMessage);
    }

    private void setUpOptionsInListBooks(MenuOption listBooksOption, ArrayList<Book> books) {
        int lengthOfBookIndexOptions = ((Integer)books.size()).toString().length();
        for (int i = 0; i < books.size(); i++) {
            String bookOptionTitle = Utilities.formatNumbersEqualStringSize(i + 1, lengthOfBookIndexOptions) +
                    ": " + bookShelf.formatItemToShowInList(books.get(i));
            MenuOption bookOption = MenuOption.createMenuOption(((Integer)(i + 1)).toString(), bookOptionTitle, "Do you wish to checkout this item?");
            setUpBookOptions(bookOption, books.get(i));
            listBooksOption.addOption(bookOption);
            bookOption.addDefaultOptionsUsingParentInfo();
        }
    }

    private void setUpBookOptions(MenuOption bookOption, Book book) {
        bookOption.addYesOption(menu, checkoutBook(book));
        bookOption.addNoOption(menu);
    }

    public void setUpMenuOptions() {
        MenuOption listBooksOption = setUpOptionToListBooks();
        //MenuOption listBooksToReturnOption = setUpOptionToListBooksToReturn();
        menu.addOption(listBooksOption);
        listBooksOption.addDefaultOptionsUsingParentInfo();
        setUpOptionsInListBooks(listBooksOption, (ArrayList<Book>) bookShelf.getAvailableItems());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BibliotecaApp app = new BibliotecaApp();
        app.setUpMenuOptions();

        System.out.println(app.welcomeMessage);

        do {
            System.out.println(app.menu.getMessageBeforeUserInput());
            System.out.println(app.menu.listOptions());

            String userInput = scanner.next();
            if (userInput.toLowerCase().equals("quit")) break;

            app.menu = app.menu.getOptionByUserInput(userInput);
        } while(true);

    }
}
