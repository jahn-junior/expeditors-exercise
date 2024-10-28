/*
 * Expeditors Coding Exercise
 */

package tests;

import model.Main;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test case for the program's driver class. Since I chose to hard code the file
 * paths for input/output and most of the methods work directly with those files, my unit
 * tests will only cover the utility methods for this class.I would also be happy to
 * present some of the unit tests I've written in the past if you'd like to see some work
 * samples.
 *
 * @author JJ Coldiron (jj.coldiron@outlook.com)
 * @author 27 Oct 2024
 */
public class MainTests {

    /** Test String for use in the tests below. */
    private String testAddress;

    /**
     * Test for Main.removeFormatting() when empty string is provided.
     */
    @Test
    void testEmptyString() {
        testAddress = "";

        String expected = "";
        String actual = Main.removeFormatting(testAddress);

        assertEquals(expected, actual);
    }

    /**
     * Test for Main.removeFormatting() when string contains extra punctuation.
     */
    @Test
    void testExtraPunctuation() {
        testAddress = "123,. Foo., Bar.. Ave,,";

        String expected = "123 foo bar ave";
        String actual = Main.removeFormatting(testAddress);

        assertEquals(expected, actual);
    }

    /**
     * Test for Main.removeFormatting() when string contains extra whitespace.
     */
    @Test
    void testExtraWhitespace() {
        testAddress = "123     Foo            Bar  Ave       ";

        String expected = "123 foo bar ave";
        String actual = Main.removeFormatting(testAddress);
        assertEquals(expected, actual);
    }

    /**
     * Test for Main.removeFormatting() with case mismatch.
     */
    @Test
    void testCaseMismatch() {
        testAddress = "123 fOo BaR aVE";

        String expected = "123 foo bar ave";
        String actual = Main.removeFormatting(testAddress);
        assertEquals(expected, actual);
    }
}
