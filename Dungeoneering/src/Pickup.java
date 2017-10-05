import java.util.*;

public class Pickup {

    static String[] berries = {"Aspear","Cheri","Chesto","Pecha","Rawst"};
    static String[] gummies = {"Black","Blue","Brown","Clear","Gold","Grass","Gray","Green","Orange","Pink","Purple","Red","Royal","Shiny","Silver","Sky","White","Yellow"};

    DungeonParser dungeon;
    Random random;
    String name;
    String out = "";
    int count = 1;

    public Pickup(DungeonParser d, String n) {
        dungeon = d;
        name = n;
        random = new Random();

        setOut();
    }

    public void setOut() {
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

    public String getName() { return name; }

    public int getCount() { return count; }

    public String toString() {
        return out;
    }

}
