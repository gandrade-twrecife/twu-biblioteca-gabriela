package com.twu.biblioteca;

import java.util.ArrayList;

public class BookShelf implements BibliotecaItemShelf {
    ArrayList<Book> books = new ArrayList<Book>();
    public String successfulCheckoutResponse = "Thank you! Enjoy the book.";
    public String unsuccessfulCheckoutResponse = "That book is not available.";
    public String itemTypeError = "This item does not belong to this shelf.";
    public String successfulReturnResponse = "Thank you for returning the book.";
    public String unsuccessfulReturnResponse = "That is not a valid book to return.";
    public static String noBooksAvailableMessage = "There are no available movies to checkout.";
    private static int sizeOfBookTitles = 70;
    private static int sizeOfBookAuthors = 50;

    public String checkoutItem(String userLogin, BibliotecaItem book){
        if (book instanceof Book) {
            if (book.checkoutItem(userLogin)) return successfulCheckoutResponse;
            else return unsuccessfulCheckoutResponse;
        }
        return itemTypeError;
    }

    public String returnItem(BibliotecaItem book){
        if (book instanceof Book) {
            if (book.returnItem()) return successfulReturnResponse;
            else return unsuccessfulReturnResponse;
        }
        return itemTypeError;
    }

    public ArrayList<? extends BibliotecaItem> getAvailableItems(){
        ArrayList<Book> availableBooks = new ArrayList<Book>();
        for (int i = 0; i < books.size(); i++) {
            Book currentBook = books.get(i);
            if (!currentBook.isBorrowed()) {
                availableBooks.add(currentBook);
            }
        }
        return availableBooks;
    }

    public ArrayList<? extends BibliotecaItem> getBorrowedItemsByUser(String userLogin) {
        ArrayList<Book> borrowedBooks = new ArrayList<Book>();
        for (int i = 0; i < books.size(); i++) {
            Book currentBook = books.get(i);
            if (currentBook.isBorrowed()) {
                if (currentBook.getBorrowedTo().equals(userLogin)) {
                    borrowedBooks.add(currentBook);
                }
            }
        }
        return borrowedBooks;
    }

    public String formatItemToShowInList(BibliotecaItem item) {
        int sizeTitle = sizeOfBookTitles;
        int sizeAuthor = sizeOfBookAuthors;
        Book book = (Book)item;

        int spacesToAdd = (sizeTitle - book.getTitle().length());
        String formattedBook = Utilities.addCharsToTheRight(book.getTitle(), spacesToAdd, ' ');

        spacesToAdd = (sizeAuthor - book.getAuthor().length());
        formattedBook += Utilities.addCharsToTheRight(book.getAuthor(), spacesToAdd, ' ');

        formattedBook += book.getYearPublished();

        return formattedBook;
    }

    public String getHeader(){
        int amountOfBooks = getAvailableItems().size();
        String year = "Year";
        String title = "Title";
        String author = "Author";
        if (amountOfBooks > 0) {
            int spacesToTheLeftOfHeaders = ((Integer) (amountOfBooks + 1)).toString().length() + 2;

            String columnsHeader = Utilities.repeatedCharacter(spacesToTheLeftOfHeaders, ' ')
                    + title + Utilities.repeatedCharacter(sizeOfBookTitles - title.length(), ' ')
                    + author + Utilities.repeatedCharacter(sizeOfBookAuthors - author.length(), ' ')
                    + year + "\n" + Utilities.repeatedCharacter(
                    spacesToTheLeftOfHeaders + sizeOfBookTitles + sizeOfBookAuthors + year.length(), '-');

            return "The books available to check out are:\n" + columnsHeader;
        }
        return noBooksAvailableMessage;
    }

    public int getAmountOfItens() {
        return books.size();
    }

    public void add(BibliotecaItem book) {
        books.add((Book) book);
    }

    public Book getBookByTitle(String title) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getTitle().equals(title))
                return books.get(i);
        }
        return null;
    }
}
