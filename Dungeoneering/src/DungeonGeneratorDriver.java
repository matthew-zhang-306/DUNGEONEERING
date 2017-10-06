import java.util.ArrayList;

/**
 * <h1>DungeonGeneratorDriver</h1>
 * The DungeonGeneratorDriver class tests the functionality of the DungeonGenerator and XPMachine classes.
 *
 * @author  myName
 * @version 1.0
 */

public class DungeonGeneratorDriver {

    public static void main(String args[]) {

        DungeonGenerator dungeon = new DungeonGenerator("moonywoods");

        dungeon.generateRooms();
        System.out.println(dungeon);

        ArrayList<PartyMember> peeps = new ArrayList<PartyMember>();
        peeps.add(new PartyMember("E"));
        peeps.add(new PartyMember("Qux"));

        XPMachine xp = new XPMachine(dungeon.getEncounters(), peeps);
        xp.giveXP();
        System.out.println(xp);


    }
}
