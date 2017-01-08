package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Scanner;

public class BibliotecaApp {

    public ArrayList<Book> books = new ArrayList<Book>();
    public final String welcomeMessage = "Welcome to the Biblioteca!";
    public final String rootMessage = "Type what is before the colon (:) to select the option.";
    private static int sizeOfBookTitles = 70;
    private static int sizeOfBookAuthors = 50;
    private User user = new User("Gabriela Andrade", "gandrade", "asdf");
    MenuOption menu = MenuOption.createMenuRoot(rootMessage);

    public BibliotecaApp(){}

    public String checkoutBook(Book book) {
        if (book.checkoutItem(user.getLogin())) {
            return "Thank you! Enjoy the book.";
        }
        else {
            return "That book is not available.";
        }
    }

    public String returnBook(Book book) {
        if (book.returnItem()) {
            return "Thank you for returning the book.";
        }
        else {
            return "That is not a valid book to return.";
        }
    }

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
    }

    public static String addCharsToTheRight(String word, int amount, char character) {
        StringBuffer output = new StringBuffer();
        output.append(word);
        for (int i = 0; i < amount; i++) {
            output.append(character);
        }
        return output.toString();
    }

    public static String showFormattedBook(Book book) {
        int sizeTitle = sizeOfBookTitles;
        int sizeAuthor = sizeOfBookAuthors;

        int spacesToAdd = (sizeTitle - book.getTitle().length());
        String formattedBook = addCharsToTheRight(book.getTitle(), spacesToAdd, ' ');

        spacesToAdd = (sizeAuthor - book.getAuthor().length());
        formattedBook += addCharsToTheRight(book.getAuthor(), spacesToAdd, ' ');

        formattedBook += book.getYearPublished();

        return formattedBook;
    }

    public static String formatNumbersEqualStringSize(Integer numberToFormat, int stringLenght) {
        String formattedNumber = numberToFormat.toString();
        int zerosToAdd = (stringLenght - formattedNumber.length());
        formattedNumber = addCharsToTheRight(formattedNumber, zerosToAdd,' ');
        return formattedNumber;
    }

    private MenuOption setUpOptionToListBooks() {
        ArrayList<Book> books = getAvailableBooks();
        int amountOfBooks = books.size();
        String listBooksOptionMessage = "There are no available books to checkoutItem.";
        if (amountOfBooks > 0) {
            int spacesToTheLeftOfHeaders = ((Integer)amountOfBooks).toString().length() + 2;
            String columnsHeader = addCharsToTheRight("", spacesToTheLeftOfHeaders, ' ')
                    + "Title";
            columnsHeader = BibliotecaApp.addCharsToTheRight(columnsHeader, sizeOfBookTitles - 5, ' ');
            columnsHeader += "Author";
            columnsHeader = BibliotecaApp.addCharsToTheRight(columnsHeader, sizeOfBookAuthors - 6, ' ');
            columnsHeader += "Year\n";
            columnsHeader = BibliotecaApp.addCharsToTheRight(columnsHeader, spacesToTheLeftOfHeaders + sizeOfBookTitles + sizeOfBookAuthors + 4, '-');

            listBooksOptionMessage = "The books available to check out are:\n" + columnsHeader;
        }
        return MenuOption.createMenuOption("1", "1: List Books.", listBooksOptionMessage);
    }

    private void setUpOptionsInListOfBooks(MenuOption listBooksOption, ArrayList<Book> books) {
        int lengthOfBookIndexOptions = ((Integer)books.size()).toString().length();
        for (int i = 0; i < books.size(); i++) {
            String bookOptionTitle = BibliotecaApp.formatNumbersEqualStringSize(i + 1, lengthOfBookIndexOptions) +
                    ": " + BibliotecaApp.showFormattedBook(books.get(i));
            MenuOption bookOption = MenuOption.createMenuOption(((Integer)(i + 1)).toString(), bookOptionTitle, "Do you wish to checkoutItem this book?");
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
        setUpOptionsInListOfBooks(listBooksOption, books);
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
