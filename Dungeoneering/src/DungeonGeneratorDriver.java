import java.util.ArrayList;

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
