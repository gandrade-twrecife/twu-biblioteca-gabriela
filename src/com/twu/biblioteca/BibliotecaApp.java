package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Scanner;

public class BibliotecaApp {

    Scanner input = new Scanner(System.in);
    public ArrayList<Book> books = new ArrayList<Book>();
    public final String welcomeMessage = "Welcome to the Biblioteca!";
    public final String rootMessage = "Type what is before the colon (:) to select the option.";

    public BibliotecaApp(){}

    public String startMenu() {
        return welcomeMessage + "\n" + ("Choose one of the following options (type its number)\n") +
                "1: List Books\n" + "Or type Quit to exit Biblioteca.\n";
    }

    public String getOutputForThisInput(String userInput) {
        if (userInput.equals("Quit")) return quitBiblioteca();
        else if (userInput.equals("1")) {
            return listBooksMenu();
        }
        else {
            return ("Select a valid option!");
        }
    }

    public String listBooksMenu() {
        ArrayList<Book> availableBooks = getAvailableBooks();
        String choice;

        while (true) {
            String bookList = listBooks();
            System.out.println(bookList);
            choice = input.next();
            int chosenBook = availableBooks.size();
            if (choice.equals("Back")) break;
            if (choice.equals("Quit")) quitBiblioteca();
            try {
                chosenBook = Integer.parseInt(choice);
            } catch (NumberFormatException ex) {
                System.out.println("This is not a number, select a valid option!");
            }
            if (chosenBook < availableBooks.size()) {
                showBookDetails(availableBooks.get(chosenBook));
            } else {
                System.out.println("Select a valid option!");
            }
        }
        return "";
    }

    public String listBooks() {
        String bookList = "Type a book's number to see details (title, author and year published):";
        ArrayList<Book> availableBooks = getAvailableBooks();
        if (availableBooks.size() == 0) {
            bookList += "\nThere are no available books in Biblioteca.";
        }
        for (int i = 0; i < availableBooks.size(); i++) {
            bookList += "\n" + i + ": " + availableBooks.get(i).getTitle();
        }
        bookList += "\nType Back to go back.";
        return bookList;
    }

    public void showBookDetails(Book book) {
        System.out.println("DETAILS:");
        System.out.println("Title: " + book.getTitle());
        System.out.println("Author: " + book.getAuthor());
        System.out.println("Year published: " + book.getYearPublished());
        String choice = "";

        do {
            System.out.println("Do you want to borrow this book? Y/N");
            choice = input.next().toUpperCase();
            if (choice.equals("Y")) {
                System.out.println(checkoutBook(book));
            }
            else if (choice.equals("N")) {
                break;
            }
            else {
                System.out.println("Please type Y or N.");
            }
        } while (!choice.equals("Y") & !choice.equals("N"));
    }

    public static String quitBiblioteca() {
        System.out.println("Thank you for visiting! Come by again!");
        System.exit(0);
        return "";
    }

    public String checkoutBook(Book book) {
        if (book.checkout()) {
            return "Thank you! Enjoy the book.";
        }
        else {
            return "That book is not available.";
        }
    }

    public String returnBook(Book book) {
        if (book.returnBook()) {
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

    public static String addCharsToTheLeft(String word, int amount, char character) {
        StringBuffer output = new StringBuffer();
        for (int i = 0; i < amount; i++) {
            output.append(character);
        }
        return output.append(word).toString();
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
        int sizeTitle = 40;
        int sizeAuthor = 30;

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

    public static String[] showFormattedBookList(ArrayList<Book> books) {
        int amountOfBooks = books.size();
        String[] formattedBookList = new String[amountOfBooks];
        for (int i = 0; i < amountOfBooks; i++) {
            String index = formatNumbersEqualStringSize(i + 1, (amountOfBooks + "").length()) + ": ";
            formattedBookList[i] = index + showFormattedBook(books.get(i));
        }
        return formattedBookList;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BibliotecaApp app = new BibliotecaApp();
        app.startMenu();

        while(true) {
            String userInput = scanner.next();
            app.getOutputForThisInput(userInput);
        }
    }
}
