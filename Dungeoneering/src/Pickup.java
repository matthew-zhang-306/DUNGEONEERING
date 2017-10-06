import java.util.*;

/**
 * <h1>Pickup</h1>
 * The Pickup class holds details for a particular dungeon pickup.
 * <p>
 *     It stores the name of the Pickup and the amount.
 * </p>
 *
 * @author  myName
 * @version 1.0
 */

public class Pickup {

    static String[] berries = {"Aspear","Cheri","Chesto","Pecha","Rawst"};
    static String[] gummies = {"Black","Blue","Brown","Clear","Gold","Grass","Gray","Green","Orange","Pink","Purple","Red","Royal","Shiny","Silver","Sky","White","Yellow"};

    DungeonParser dungeon;
    Random random;
    String name;
    String out = "";
    int count = 1;

    /**
     * Constructor for a Pickup object. Automatically rolls for its type.
     * @param d The DungeonParser being used
     * @param n The name of the pickup
     */
    public Pickup(DungeonParser d, String n) {
        dungeon = d;
        name = n;
        random = new Random();

        setOut();
    }

    /**
     * This method uses the name of the object to generate any special
     * information about that particular type of object.
     * <p>
     *     It is automatically called by the constructor.
     * </p>
     */
    private void setOut() {
        // BERRY
        if (name.equals("Berry")) {
            int roll = random.nextInt(berries.length);
            out = berries[roll] + " " + name;
        }
        // GUMMI
        else if (name.equals("Gummi")) {
            int stat = random.nextInt(100);
            if (stat < 20) {
                int roll = random.nextInt(gummies.length);
                out = gummies[roll] + " " + name;
            } else if (stat < 50) {
                out = "Unfavorable " + name;
            } else {
                out = "Favorable " + name;
            }
        }
        // POKE
        else if (name.equals("Poke")) {
            int range = dungeon.getPokeMax() - dungeon.getPokeMin() + 1;
            count = random.nextInt(range) + dungeon.getPokeMin();
            out = name;
        }
        // DEFAULT
        else {
            out = name;
        }

    }

    /**
     * Returns the name of the pickup.
     * @return name
     */
    public String getName() { return name; }
    /**
     * Returns the amount of the pickup.
     * @return count
     */
    public int getCount() { return count; }
    /**
     * Returns the full, specialized name of the pickup.
     * @return out
     */
    public String toString() {
        return out;
    }

}
