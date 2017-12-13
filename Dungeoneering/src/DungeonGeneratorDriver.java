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

        DungeonGenerator dungeon = new DungeonGenerator("waterfallcave");

        dungeon.generateRooms();
        System.out.println(dungeon);

        ArrayList<PartyMember> peeps = new ArrayList<PartyMember>();
        peeps.add(new PartyMember("Allie"));
        peeps.add(new PartyMember("Ifreet"));
        peeps.add(new PartyMember("Ebony"));
        peeps.add(new PartyMember("Shimmer"));
        peeps.add(new PartyMember("Alex"));
        peeps.add(new PartyMember("E"));
        peeps.add(new PartyMember("Ranma"));


        XPMachine xp = new XPMachine(dungeon.getEncounters(), peeps);
        xp.giveXP();
        System.out.println(xp);


    }
}
