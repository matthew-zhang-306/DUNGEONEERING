import java.io.*;
import java.util.*;

public class Encounter {

    DungeonParser dungeon;
    String name;
    int level;
    int xp;

    public Encounter(DungeonParser d, String n, int l) {
        dungeon = d;
        name = n;
        level = l;

        calculateXP();
    }

    public void calculateXP() {
        Scanner scan = null;
        try {
            scan = new Scanner(new File("xpguide"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String[] stats = null;
        while (scan.hasNext()) {
            stats = scan.nextLine().split(" ");
            if (stats[0].equals(name)) break;
        }

        xp = (int)Math.round(Double.parseDouble(stats[1]) * level / 7.0);

    }

    public int getXP() {
        return xp;
    }


    public String toString() {
        return name + "LV" + level;

    }

}
