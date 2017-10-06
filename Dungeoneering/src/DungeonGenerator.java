import java.util.*;

/**
 * <h1>DungeonGenerator</h1>
 * The DungeonGenerator class generates a random dungeon run for a particular dungeon.
 * It uses data from a DungeonParser to roll for floors and populate them.
 * <p>
 *     It stores the collective spoils and encounters from the dungeon run.
 * </p>
 *
 * @author  myName
 * @version 1.0
 */

public class DungeonGenerator {

    String header = "" +
            "****************************************************\n" +
            "*                                                   \n" +
            "*       TEXT                                        \n" +
            "*                                                   \n" +
            "****************************************************\n";
    String semiheader = "" +
            "****************************************************\n" +
            "*       TEXT                                        \n" +
            "****************************************************\n";

    DungeonParser dungeon;
    Random random;
    Room[][] rooms;
    String name;

    ArrayList<String> spoils = new ArrayList<String>();
    ArrayList<Integer> counts = new ArrayList<Integer>();
    ArrayList<Encounter> encounters = new ArrayList<Encounter>();

    /**
     * Constructor for a DungeonParser object.
     * @param d The name of the dungeon
     */
    public DungeonGenerator(String d) {
        name = d;
        dungeon = new DungeonParser(d);
        random = new Random();
        rooms = new Room[dungeon.floors][];
    }

    /**
     * This method creates a ragged array of rooms in which each floor has a
     * random number of rooms within the range specified on the dungeon document.
     */
    public void generateRooms() {
        for (int r = 0; r < rooms.length; r++) {
            int range = dungeon.getRoomsMax() - dungeon.getRoomsMin() + 1;
            rooms[r] = new Room[random.nextInt(range) + dungeon.getRoomsMin()];
            boolean mhouse = random.nextInt(100) < dungeon.getMonsterHouseChance();
            int m = mhouse ? random.nextInt(rooms[r].length) : -1;

            for (int c = 0; c < rooms[r].length; c++) {
                populateRooms(r,c,m,dungeon);
            }
        }

    }

    /**
     * This method generates a particular room, assigning its type and populating it
     * with items, traps, and encounters.
     * <p>
     *     It also stores the items and encounters in respective arrays.
     * </p>
     * @param r The row index of the room
     * @param c The column index of the room
     * @param m The index of room that should be a monster house on the floor
     * @param dungeon The DungeonParser being used
     */
    private void populateRooms(int r, int c, int m, DungeonParser dungeon) {
        boolean big = random.nextInt(100) < dungeon.getBigRoomChance();
        boolean monster = (c == m);

        rooms[r][c] = new Room(dungeon, big, monster);
        rooms[r][c].populate();

        // add to spoils
        Pickup[] stuff = rooms[r][c].getItems();
        for (int i = 0; i < stuff.length; i++) {
            boolean found = false;
            for (int j = 0; j < spoils.size(); j++) {
                if (stuff[i].toString().equals(spoils.get(j))) {                                /*<-----------NEEDS ATTENTION: OCCASIONAL NULLPOINTEREXCEPTION THROWN*/
                    counts.set(j, counts.get(j) + stuff[i].getCount());
                    found = true;
                    break;
                }
            }
            if (!found) {
                spoils.add(stuff[i].toString());
                counts.add(stuff[i].getCount());
            }
        }

        // add to encounters
        Encounter[] pokemon = rooms[r][c].getEncounters();
        for (int k = 0; k < pokemon.length; k++) {
            encounters.add(pokemon[k]);
        }
    }

    /**
     * Returns the collective array of encounters in the dungeon.
     * @return encounters[]
     */
    public ArrayList<Encounter> getEncounters() { return encounters; }

    /**
     * Returns a printout for the DungeonGenerator object.
     * Includes a summary and detailed diagnostics for each floor.
     * @return this as a String
     */
    public String toString() {
        String output = header.replace("TEXT","Dungeon " + name.toUpperCase() + " Summary");

        // summary (items)
        output += "GAINED: ";
        for (int s = 0; s < spoils.size(); s++) {
            output += counts.get(s) + " " + spoils.get(s);
            if (s != spoils.size()-1)
                output += ", ";
        }
        output += "\n";
        // summary (encounters)
        output += "ENCOUNTERED: ";
        for (int s = 0; s < encounters.size(); s++) {
            output += encounters.get(s);
            if (s != encounters.size()-1)
                output += ", ";
            if (s % 15 == 14)
                output += "\n";
        }
        output += "\n";
        // detailed diagnostics (floors)
        for (int r = 0; r < rooms.length; r++) {
            output += semiheader.replace("TEXT", "Floor " + (r+1));
            for (int c = 0; c < rooms[r].length; c++) {
                output += rooms[r][c] + "\n";
            }
        }

        return output;

    }

}
