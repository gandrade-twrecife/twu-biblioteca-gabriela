package com.twu.biblioteca;

public class Utilities {

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
}
