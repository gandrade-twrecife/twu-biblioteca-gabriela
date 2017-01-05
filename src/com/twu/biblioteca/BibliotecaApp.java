package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Scanner;

public class BibliotecaApp {

    Scanner input = new Scanner(System.in);
    public ArrayList<Book> books = new ArrayList<Book>();
    private String welcome_message = "Welcome to the Biblioteca!";
    public String customer;
    ArrayList<Book> borrowed_books;

    public BibliotecaApp(){}

    public void whoAreYou() {
        System.out.println("Please type you name.");
        Scanner input = new Scanner(System.in);
        customer = input.next();
        borrowed_books = getBooksBorrowedByCustomer(customer);
    }

    public void startMenu() {
        System.out.println("Hello " + customer + "!");
        System.out.println(welcome_message);
        System.out.println("You have borrowed " + borrowed_books.size() + " books.");
        String choice;
        do {
            System.out.println("Choose one of the following options (type its number)");
            System.out.println("1: List Books");
            System.out.println("Or type Quit to exit Biblioteca.");

            choice = input.next();
            if (choice == 1 + "") {
                listBooksMenu();
            } else {
                System.out.println("Select a valid option!");
            }
        } while (choice != "Quit");
        quitBiblioteca();
    }

    public void listBooksMenu() {
        ArrayList<Book> available_books = getNotBorrowedBooks();
        String choice = "";

        while (choice != "Back") {
            listBooks();
            choice = input.next();
            int chosen_book = available_books.size();
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

    public void listBooks() {
        System.out.println("Type a book's number to see details (title, author and year published):");
        ArrayList<Book> available_books = getNotBorrowedBooks();
        for (int i = 0; i < available_books.size(); i++) {
            System.out.println(i + ": " + available_books.get(i).title);
        }
        System.out.println("Or type Back to go back.");
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
            if (choice == "Y") {
                book.checkout(customer);
            }
            else if (choice == "N") {
                break;
            }
            else {
                System.out.println("Please type Y or N.");
            }
        } while (choice != "Y" & choice != "N");
    }

    public void quitBiblioteca() {
        System.out.println("Thank you for visiting! Come by again!");
    }

    public void checkoutBook(int book_index) {
        books.get(book_index).checkout(customer);
    }

    public void returnBook(int book_index) {
        books.get(book_index).returnBook();
    }

    public ArrayList<Book> getNotBorrowedBooks() {
        ArrayList<Book> not_borrowed_books = new ArrayList<Book>();
        for(int i = 0; i < books.size(); i++) {
            if (!books.get(i).borrowed) {
                not_borrowed_books.add(books.get(i));
            }
        }
        return not_borrowed_books;
    }

    public ArrayList<Book> getBooksBorrowedByCustomer(String customer) {
        ArrayList<Book> borrowed_by_customer = new ArrayList<Book>();
        for(int i = 0; i < books.size(); i++) {
            if (books.get(i).borrowed_to == customer) {
                borrowed_by_customer.add(books.get(i));
            }
        }
        return borrowed_by_customer;
    }

    public static void main(String[] args) {
        BibliotecaApp app = new BibliotecaApp();
        app.whoAreYou();
        app.startMenu();
        //app.seeChoices();
    }
}
