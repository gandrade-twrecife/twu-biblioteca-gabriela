package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Scanner;

public class BibliotecaApp {

    public ArrayList<Book> books = new ArrayList<Book>();
    private String welcome_message = "Welcome to the Biblioteca!";
    private String customer;
    ArrayList<Book> borrowed_books;

    public BibliotecaApp(){}

    public void whoAreYou() {
        System.out.println("Please type you name.");
        Scanner input = new Scanner()
    }

    public void showInitialMenu() {
        System.out.println(welcome_message);
        System.out.println("You have borrowed " + borrowed_books.size() + " books.");
        System.out.println("Choose one of the following options (type its number)");
        System.out.println("1: List Books");
        System.out.println("Or type Quit to exit Biblioteca.");
    }

    public void quitBiblioteca() {
        System.out.println("Thank you for visiting! Come by again!");
    }

    public void checkoutBook(int book_index, String customer) {
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
        System.out.println("Hello, world!");
    }
}
