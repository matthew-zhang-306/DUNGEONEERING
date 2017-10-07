import java.io.*;
import java.util.*;

/**
 * <h1>Encounter</h1>
 * The Encounter class holds details for a particular dungeon encounter.
 * <p>
 *     It stores the name and level of the Pokemon and calculates the given xp.
 * </p>
 *
 * @author  myName
 * @version 1.0
 */

public class Encounter {

    String name;
    int level;
    int xp;

    /**
     * Constructor for an Encounter object. Automatically calculates given xp.
     * @param n The name of the Pokemon
     * @param l The level of the Pokemon
     */
    public Encounter(String n, int l) {
        name = n;
        level = l;

        calculateXP();
    }

    /**
     * This method calculates the given xp of the encounter. It requires that
     * the file "xpguide" exists and contains the Pokemon's stats.
     * <p>
     *     It is automatically called by the constructor.
     * </p>
     */
    private void calculateXP() {
        // set up scanner
        Scanner scan = null;
        try {
            scan = new Scanner(new File("xpguide"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // extract data
        String[] stats = null;
        while (scan.hasNext()) {
            stats = scan.nextLine().split(" ");
            if (stats[0].equals(name)) break;
        }

        xp = (int)Math.floor(Double.parseDouble(stats[1]) * level / 7.0);

    }

    /**
     * Returns the given xp count of the encounter.
     * @return xp
     */
    public int getXP() {
        return xp;
    }

    /**
     * Returns a printout for the Encounter object.
     * Includes the name and the level of the Pokemon.
     * @return this as a String
     */
    public String toString() {
        return name + "LV" + level;

    }

}
