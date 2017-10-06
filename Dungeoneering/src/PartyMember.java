import java.util.*;
import java.io.*;

/**
 * <h1>PartyMember</h1>
 * The PartyMember class holds details for a particular party member.
 * <p>
 *     It stores the name, level, and leveling speed of the Pokemon.
 * </p>
 *
 * @author  myName
 * @version 1.0
 */

public class PartyMember {

    String name;
    String xpspeed;
    int level;
    int xp;

    /**
     * Constructor for a PartyMember object. Automatically fetches other stats.
     * @param n The name of the Pokemon
     */
    public PartyMember(String n) {
        name = n;

        getStats();
    }

    /**
     * This method reads a file to find data for other stats with the Pokemon's name.
     * It requires that the file "rpers" exists and contains the Pokemon's stats.
     * <p>
     *     It is automatically called by the constructor.
     * </p>
     */
    private void getStats() {
        Scanner scan = null;
        try {
            scan = new Scanner(new File("rpers"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String[] stats = null;
        while (scan.hasNext()) {
            stats = scan.nextLine().split(" ");
            if (stats[0].equals(name)) break;
        }
        level = Integer.parseInt(stats[1]);
        xp = Integer.parseInt(stats[2]);
        xpspeed = stats[3];

    }

    /**
     * Returns the name of the party member.
     * @return name
     */
    public String getName() { return name; }
    /**
     * Returns the level of the party member.
     * @return level
     */
    public int getLevel() { return level; }
    /**
     * Returns the total xp of the party member.
     * @return xp
     */
    public int getXP() { return xp; }
    /**
     * Returns the leveling speed of the party member.
     * @return xpspeed
     */
    public String getXPspeed() { return xpspeed; }


    /**
     * This method adds a given amount of xp to the party member's total xp.
     * It also uses that number to recalculate the party member's level.
     * @param a The amount of xp to add
     */
    public void addXP(int a) {
        xp += a;

        // GET LEVEL
        for (int i = level; i < 100; i++) {
            if (levelReq(xpspeed, i) > xp) {
                level = i-1;
                break;
            }
        }
    }

    /**
     * This method calculates the amount of xp needed to get a certain level.
     * <p>
     *     Details on the formulas can be found on the Bulbapedia page on Experience.
     * </p>
     * @param speed The leveling speed of the Pokemon
     * @param level The level in question
     * @return The amount of xp needed to reach the level in question
     */
    private int levelReq(String speed, int level) {
        int output = (int)Math.pow(level, 3);
        if (speed.equals("fast")) {
            output *= 4/5;
        }
        else if (speed.equals("slow")) {
            output *= 5/4;
        }
        else if (speed.equals("mediumslow")) {
            double temp = output * 6.0 / 5.0;
            output = (int)(temp - (15 * (int)Math.pow(level, 2)) + (100 * level) - 140);
        }

        return output;
    }

}
