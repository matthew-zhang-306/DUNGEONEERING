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

        DungeonGenerator dungeon = new DungeonGenerator("rightsweetmountain");

        dungeon.generateRooms();
        System.out.println(dungeon);

        ArrayList<PartyMember> peeps = new ArrayList<PartyMember>();
        peeps.add(new PartyMember("Ranma"));
        peeps.add(new PartyMember("Ifreet"));

        XPMachine xp = new XPMachine(dungeon.getEncounters(), peeps);
        xp.giveXP();
        System.out.println(xp);


    }
}
