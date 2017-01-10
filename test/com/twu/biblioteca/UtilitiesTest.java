package com.twu.biblioteca;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UtilitiesTest {

    @Test
    public void formatNumbersToStringOfLenght1() {
        assertEquals("1", Utilities.formatNumbersEqualStringSize(1, 1));
    }

    @Test
    public void formatNumbersToStringOfSameLenght() {
        assertEquals("1", Utilities.formatNumbersEqualStringSize(1, 1));
        assertEquals("1 ", Utilities.formatNumbersEqualStringSize(1, 2));
        assertEquals("1  ", Utilities.formatNumbersEqualStringSize(1, 3));
        assertEquals("1   ", Utilities.formatNumbersEqualStringSize(1, 4));
    }

    @Test
    public void addCharsToTheRightTest() {
        String original = "teste";
        String expected = "teste     ";
        assertEquals(expected, Utilities.addCharsToTheRight(original, 5, ' '));
    }

    @Test
    public void addZeroCharsToTheRightTest() {
        String original = "teste";
        String expected = original;
        assertEquals(expected, Utilities.addCharsToTheRight(original, 0, ' '));
    }
}
