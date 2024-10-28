/*
 * Expeditors Coding Exercise
 */

package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Driver class for program. Handles file input/output and core logic.
 *
 * @author JJ Coldiron (jj.coldiron@outlook.com)
 * @version 27 Oct 2024
 */
public class Main {

    // Class Constants
    /** Maps household address to number of residents. */
    private static final HashMap<String, Integer> HOUSEHOLDS = new HashMap<>();

    /** Contains all occupant data. Chose array list for sorting efficiency. */
    private static final ArrayList<Occupant> OCCUPANTS = new ArrayList<>();

    /**
     * Starting point for program execution. Handles file operations and calls helper
     * methods.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        Scanner fileIn = null;
        PrintStream fileOut = null;

        try {
            fileIn = new Scanner(new File("./files/input.txt"));
            fileOut = new PrintStream("./files/output.txt");
        } catch (FileNotFoundException fnf) {
            System.err.println("Invalid filepath: " + fnf);
        }

        if (fileIn != null) {
            readFileInput(fileIn);
            fileIn.close();
        }

        // See compareTo() in Occupant.java
        Collections.sort(OCCUPANTS);

        if (fileOut != null) {
            produceOutput(fileOut);
            fileOut.close();
        }
    }

    /**
     * Contains the bulk of the program logic including parsing input and storing data
     * in the data structures defined at the top of the class.
     *
     * @param theInput Input file opened in main.
     */
    private static void readFileInput(Scanner theInput) {

        // Declared outside of loop for efficiency.
        String fName, lName, address, city, state, age;

        while (theInput.hasNextLine()) {
            Scanner lineScan = new Scanner(theInput.nextLine());
            lineScan.useDelimiter(Pattern.compile("(\",\")|\"", Pattern.CASE_INSENSITIVE));

            fName = lineScan.next();
            lName = lineScan.next();
            address = lineScan.next();
            city = lineScan.next();
            state = lineScan.next();
            age = lineScan.next();

            String household = address + " " + city + " " + state;
            household = removeFormatting(household);
            OCCUPANTS.add(new Occupant(fName, lName, household, age));

            /*
             * Checks if household (key) already exists in map. Adds to key set if not and
             * updates occupant count (value) if the key already exists.
             */
            if (HOUSEHOLDS.containsKey(household.toLowerCase())) {
                int occupantCount = HOUSEHOLDS.get(household.toLowerCase());
                HOUSEHOLDS.put(household.toLowerCase(), occupantCount + 1);
            } else {
                HOUSEHOLDS.put(household.toLowerCase(), 1);
            }
        }
    }

    /**
     * Helper method to remove formatting inconsistencies between address entries. I operated
     * under the assumption that any inconsistencies would be limited to punctuation and
     * capitalization differences. If any other problems arose, this method could be easily
     * expanded, as seen in the commented out section below. I also chose to work with all
     * addresses in lower case and without punctuation, as I felt this was as "neutral" as
     * the data could be.
     *
     * @param theOriginal The address string being formatted.
     * @return The lowercase address with all punctuation removed.
     */
    public static String removeFormatting(String theOriginal) {
        return theOriginal.toLowerCase().replaceAll("[.,]", "")
                .replaceAll(" {2,}", " ").replaceAll("( *$)", "");

//        result = result.replaceAll("(boulevard)", "blvd")
//                .replaceAll("(street)", "st")
//                .replaceAll("(avenue)", "ave")
//                .replaceAll("(apartment)", "apt");
    }

    /**
     * Displays the household and number of occupants, followed by each first name, last name,
     * address, and age sorted by last name and then first name where the occupant is older
     * than 18.
     *
     * @param theOut PrintStream for displaying output. Writes output to ./files/output.txt
     */
    private static void produceOutput(PrintStream theOut) {
        theOut.println("Households: ");
        for (String house : HOUSEHOLDS.keySet()) {
            theOut.print(house + ": " + HOUSEHOLDS.get(house) + " resident(s)\n");
        }

        theOut.println("\nOccupants: ");
        for (Occupant occ : OCCUPANTS) if (occ.getAge() > 18) theOut.println(occ);

    }

}
