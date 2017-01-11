package com.twu.biblioteca;

public class Utilities {
    public static final int sizeOfBookTitles = 70;
    public static final int sizeOfBookAuthors = 50;
    public static final int sizeOfMovieNames = 60;
    public static final int sizeOfMovieDirectors = 40;

    public static String addCharsToTheRight(String word, int amount, char character) {
        StringBuffer output = new StringBuffer();
        output.append(word);
        for (int i = 0; i < amount; i++) {
            output.append(character);
        }
        return output.toString();
    }

    public static String formatNumbersEqualStringSize(Integer numberToFormat, int stringLenght) {
        String formattedNumber = numberToFormat.toString();
        int zerosToAdd = (stringLenght - formattedNumber.length());
        formattedNumber = Utilities.addCharsToTheRight(formattedNumber, zerosToAdd,' ');
        return formattedNumber;
    }

    public static String repeatedCharacter(int size, char character) {
        String wordWithRepeatedCharacter = "";
        for (int i = 0; i < size; i++) {
            wordWithRepeatedCharacter += character;
        }
        return wordWithRepeatedCharacter;
    }

    public static String formatItemToShowInList(BibliotecaItem item) {
        if (item instanceof Book) {
            return formatBookToShowInList((Book)item);
        }
        else if (item instanceof Movie) {
            return formatMovieToShowInList((Movie)item);
        }
        else return null;
    }

    public static String formatBookToShowInList(Book book) {
        int sizeTitle = sizeOfBookTitles;
        int sizeAuthor = sizeOfBookAuthors;

        int spacesToAdd = (sizeTitle - book.getTitle().length());
        String formattedBook = Utilities.addCharsToTheRight(book.getTitle(), spacesToAdd, ' ');

        spacesToAdd = (sizeAuthor - book.getAuthor().length());
        formattedBook += Utilities.addCharsToTheRight(book.getAuthor(), spacesToAdd, ' ');

        formattedBook += book.getYearPublished();

        return formattedBook;
    }

    public static String formatMovieToShowInList(Movie movie) {
        int sizeTitle = sizeOfMovieNames;
        int sizeAuthor = sizeOfMovieDirectors;

        int spacesToAdd = (sizeTitle - movie.getName().length());
        String formattedMovie = Utilities.addCharsToTheRight(movie.getName(), spacesToAdd, ' ');

        spacesToAdd = (sizeAuthor - movie.getDirector().length());
        formattedMovie += Utilities.addCharsToTheRight(movie.getDirector(), spacesToAdd, ' ');

        formattedMovie += movie.getYear() + " " + movie.getMovieRating();

        return formattedMovie;
    }
}
