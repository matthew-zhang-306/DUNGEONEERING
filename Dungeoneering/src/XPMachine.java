import java.util.*;

/**
 * <h1>XPMachine</h1>
 * The XPMachine class takes a list of encounters and a list of party members
 * in a dungeon and distributes experience points to the party.
 * <p>
 *     It allows for the manual addition of specific encounters as well.
 * </p>
 *
 * @author  myName
 * @version 1.0
 */

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

    /**
     * Constructor for an XPMachine object.
     * @param p An array of party members
     */
    public XPMachine (ArrayList<PartyMember> p) {
        encounters = new ArrayList<Encounter>();
        party = p;
        total = 0;
    }

    /**
     * Overloaded constructor for an XPMachine object.
     * @param e An array of encounters
     * @param p An array of party members
     */
    public XPMachine (ArrayList<Encounter> e, ArrayList<PartyMember> p) {
        encounters = e;
        party = p;
        total = 0;
    }

    /**
     * This method adds an encounter to the list to be factored in for xp gain.
     * Meant to be done before using giveXP().
     * @param e The encounter to add
     */
    public void addEncounter(Encounter e) {
        encounters.add(e);
    }

    /**
     * This method blasts out xp to all the party members.
     */
    public void giveXP() {
        totalXP();

        for (int i = 0; i < party.size(); i++) {
            party.get(i).addXP(total / party.size());
        }

    }
    /**
     * Helper for giveXP(): totals the xp gained from each encounter in the list.
     */
    private void totalXP() {
        for (int i = 0; i < encounters.size(); i++) {
            total += encounters.get(i).getXP();
        }

    }

    /**
     * Returns a list of the party members involved.
     * @return party
     */
    public ArrayList<PartyMember> getParty() {
        return party;
    }

    /**
     * Returns a printout for the XPMachine object.
     * Includes xp and level gain stats for each party member.
     * @return this as a String
     */
    public String toString() {
        String output = header;

        for (int i = 0; i < party.size(); i++) {
            output += party.get(i).getProgress() + " ";
            output += party.get(i).getName().toUpperCase() + " has " + party.get(i).getXP() + " XP (LEVEL " + party.get(i).getLevel() + ")" + "\n";
        }

        return output;
    }

}
