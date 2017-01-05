package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Scanner;

public class BibliotecaApp {

    Scanner input = new Scanner(System.in);
    public ArrayList<Book> books = new ArrayList<Book>();
    private String welcome_message = "Welcome to the Biblioteca!";

    public BibliotecaApp(){}

    public void startMenu() {
        System.out.println(welcome_message);
        String choice;
        while (true) {
            System.out.println("Choose one of the following options (type its number)");
            System.out.println("1: List Books");
            System.out.println("Or type Quit to exit Biblioteca.");
            choice = input.next();
            if (choice.equals("Quit")) break;
            else if (choice.equals("1")) {
                listBooksMenu();
            }
            else {
                System.out.println("Select a valid option!");
            }
        }
        quitBiblioteca();
    }

    public void listBooksMenu() {
        ArrayList<Book> available_books = getAvailableBooks();
        String choice;

        while (true) {
            String book_list = listBooks();
            System.out.println(book_list);
            choice = input.next();
            int chosen_book = available_books.size();
            if (choice.equals("Back")) break;
            try {
                chosen_book = Integer.parseInt(choice);
            } catch (NumberFormatException ex) {
                System.out.println("This is not a number, select a valid option!");
            }
            if (chosen_book < available_books.size()) {
                showBookDetails(available_books.get(chosen_book));
            } else {
                System.out.println("Select a valid option!");
            }
        }
    }

    public String listBooks() {
        String book_list = "Type a book's number to see details (title, author and year published):";
        ArrayList<Book> available_books = getAvailableBooks();
        if (available_books.size() == 0) {
            book_list += "\nThere are no available books in Biblioteca.";
        }
        for (int i = 0; i < available_books.size(); i++) {
            book_list += "\n" + i + ": " + available_books.get(i).title;
        }
        book_list += "\nType Back to go back.";
        return book_list;
    }

    public void showBookDetails(Book book) {
        System.out.println("DETAILS:");
        System.out.println("Title: " + book.title);
        System.out.println("Author: " + book.author);
        System.out.println("Year published: " + book.year_published);
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

    public void quitBiblioteca() {
        System.out.println("Thank you for visiting! Come by again!");
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
        ArrayList<Book> not_borrowed_books = new ArrayList<Book>();
        if (books != null) {
            for (int i = 0; i < books.size(); i++) {
                if (!books.get(i).isBorrowed()) {
                    not_borrowed_books.add(books.get(i));
                }
            }
        }
        return not_borrowed_books;
    }

    public static void main(String[] args) {
        BibliotecaApp app = new BibliotecaApp();
        app.startMenu();
    }
}
