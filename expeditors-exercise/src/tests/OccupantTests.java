/*
 * Expeditors Coding Exercise
 */

package tests;

import model.Occupant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test case for Occupant.java
 *
 * @author JJ Coldiron (jj.coldiron@outlook.com)
 * @author 27 Oct 2024
 */
public class OccupantTests {

    /** Occupant instance to be used during tests. */
    private Occupant myOccupant;

    /**
     * Resets myOccupant to same initial values to preserve valid state between tests.
     */
    @BeforeEach
    void setUp() {
        myOccupant = new Occupant("Foo",
                "Bar",
                "123 foo bar ave",
                "64");
    }

    /**
     * Test for Occupant.getFirst()
     */
    @Test
    void getFirstTest() {
        String expected = "Foo";
        String actual = myOccupant.getFirst();
        assertEquals(expected, actual);
    }

    /**
     * Test for Occupant.getLast()
     */
    @Test
    void getLastTest() {
        String expected = "Bar";
        String actual = myOccupant.getLast();
        assertEquals(expected, actual);
    }

    /**
     * Test for Occupant.getAge()
     */
    @Test
    void getAgeTest() {
        int expected = 64;
        int actual = myOccupant.getAge();
        assertEquals(expected, actual);
    }

    /**
     * Test for Occupant.compareTo() when compared against a "lesser" last name.
     */
    @Test
    void testCompareToLesserLastName() {
        Occupant newGuy = new Occupant("Foo",
                "Bab",
                "address",
                "64");
        int result = myOccupant.compareTo(newGuy);
        assert(result > 0);
    }

    /**
     * Test for Occupant.compareTo() when compared to a "greater" last name.
     */
    @Test
    void testCompareToGreaterLastName() {
        Occupant newGuy = new Occupant("Foo",
                "Car",
                "address",
                "64");
        int result = myOccupant.compareTo(newGuy);
        assert(result < 0);
    }

    /**
     * Test for Occupant.compareTo() when compared to an occupant with the same last name
     * and a "lesser" first name.
     */
    @Test
    void testCompareToSameLastName() {
        Occupant newGuy = new Occupant("Boo",
                "Bar",
                "address",
                "64");
        int result = myOccupant.compareTo(newGuy);
        assert(result > 0);
    }

    /**
     * Test for Occupant.compareTo() when compared to an occupant with the same first and
     * last name.
     */
    @Test
    void testCompareToSameName() {
        Occupant newGuy = new Occupant("Foo",
                "Bar",
                "address",
                "64");
        int expected = 0;
        int actual = myOccupant.compareTo(newGuy);
        assertEquals(expected, actual);
    }

    /**
     * Test for Occupant.toString()
     */
    @Test
    void testToString() {
        String expected = "Foo Bar; 123 foo bar ave; Age: 64";
        String actual = myOccupant.toString();
        assertEquals(expected, actual);
    }
}
