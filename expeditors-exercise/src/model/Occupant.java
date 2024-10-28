/*
 * Expeditors Coding Exercise
 */

package model;

/**
 * Contains occupant state and associated methods. While a separate class was not strictly
 * necessary, I thought it made for better encapsulation.
 *
 * @author JJ Coldiron (jj.coldiron@outlook.com)
 * @version 27 Oct 2024
 */
public class Occupant implements Comparable<Occupant> {

    /** Occupant's first name. */
    private final String myFirst;

    /** Occupant's last name. */
    private final String myLast;

    /** Occupant's address. */
    private final String myAddress;

    /** Occupant's age. */
    private final int myAge;

    /**
     * Initializes instance fields to given values. I operated under the assumption that all
     * data provided in the input file will be valid (i.e. age will be positive numeric value).
     *
     * @param theFirst First name from input file.
     * @param theLast Last name from input file.
     * @param theAddress Address from input file.
     * @param theAge Age from input file.
     */
    public Occupant(final String theFirst, final String theLast, final String theAddress,
                    final String theAge) {
        myFirst = theFirst;
        myLast = theLast;
        myAddress = theAddress;
        myAge = Integer.parseInt(theAge);
    }

    /**
     * Accessor method for occupant first name.
     *
     * @return Occupant's first name.
     */
    public String getFirst() { return myFirst; }

    /**
     * Accessor method for occupant last name.
     *
     * @return Occupant's last name.
     */
    public String getLast() { return myLast; }

    /**
     * Accessor method for occupant's age.
     *
     * @return Occupant's age.
     */
    public int getAge() { return myAge; }

    /**
     * Allows comparison between two different occupants. This was written so that
     * Collections.sort() could be used on the occupants list in the driver class.
     * Sorts by last name, then first name.
     *
     * @param theOther The occupant to be compared against.
     * @return Integer difference between the occupants' last names, then first names.
     */
    public int compareTo(Occupant theOther) {
        int result = myLast.compareToIgnoreCase(theOther.getLast());
        if (result == 0) result = myFirst.compareToIgnoreCase(theOther.getFirst());
        return result;
    }

    /**
     * String representation of the occupant per the exercise specifications.
     *
     * @return String representation of the occupant.
     */
    @Override
    public String toString() {
        return myFirst + " " + myLast + "; " + myAddress + "; " + "Age: " + myAge;
    }

}
