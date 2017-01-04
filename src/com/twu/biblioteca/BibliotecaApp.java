package com.twu.biblioteca;

import java.util.ArrayList;

public class BibliotecaApp {

    public BibliotecaApp(){}

    public ArrayList<Book> books = new ArrayList<Book>();

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
