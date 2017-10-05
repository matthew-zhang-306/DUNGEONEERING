import java.util.*;

public class XPMachine {

    String header = "" +
            "  ######   ###########     \n" +
            " #      # #           #    \n" +
            "#   XP   #   SUMMARY   #   \n" +
            " #      # #           #    \n" +
            "  ######   ###########     \n";

    ArrayList<Encounter> encounters;
    ArrayList<PartyMember> party;
    int total;

    public XPMachine () {
        encounters = new ArrayList<Encounter>();
        party = new ArrayList<PartyMember>();
        total = 0;
    }

    public XPMachine (ArrayList<Encounter> e, ArrayList<PartyMember> p) {
        encounters = e;
        party = p;
        total = 0;
    }

    public void addEncounter(Encounter e) {
        encounters.add(e);
    }

    public void giveXP() {
        totalXP();

        for (int i = 0; i < party.size(); i++) {
            party.get(i).addXP(total / party.size());
        }

    }

    public void totalXP() {
        for (int i = 0; i < encounters.size(); i++) {
            total += encounters.get(i).getXP();
        }

    }

    public String toString() {
        String output = header;

        for (int i = 0; i < party.size(); i++) {
            output += party.get(i).getName().toUpperCase() + " has " + party.get(i).getXP() + " XP (LEVEL " + party.get(i).getLevel() + ")" + "\n";
        }

        return output;
    }

}
