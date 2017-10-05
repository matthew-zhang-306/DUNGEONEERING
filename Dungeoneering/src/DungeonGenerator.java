import java.util.*;

/**
 * DungeonGenerator
 *
 * Generates a random dungeon run using data from the DungeonParser
 *
 * v. 1.0
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

    //int totalxp = 0;

    public DungeonGenerator(String d) {
        name = d;
        dungeon = new DungeonParser(d);
        random = new Random();
        rooms = new Room[dungeon.floors][];
    }

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

    public void populateRooms(int r, int c, int m, DungeonParser dungeon) {
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

    public ArrayList<Encounter> getEncounters() { return encounters; }

    public String toString() {
        String output = header.replace("TEXT","Dungeon " + name.toUpperCase() + " Summary");

        output += "GAINED: ";
        for (int s = 0; s < spoils.size(); s++) {
            output += counts.get(s) + " " + spoils.get(s);
            if (s != spoils.size()-1)
                output += ", ";
        }
        output += "\n";

        output += "ENCOUNTERED: ";
        for (int s = 0; s < encounters.size(); s++) {
            output += encounters.get(s);
            if (s != encounters.size()-1)
                output += ", ";
            if (s % 15 == 14)
                output += "\n";
        }
        output += "\n";

        for (int r = 0; r < rooms.length; r++) {
            output += semiheader.replace("TEXT", "Floor " + (r+1));
            for (int c = 0; c < rooms[r].length; c++) {
                output += rooms[r][c] + "\n";
            }
        }

        return output;

    }

}
