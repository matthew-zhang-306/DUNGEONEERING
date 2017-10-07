import java.util.*;
import java.io.*;

public class XPMachineDriver {

    public static void main(String args[]) {

        ArrayList<PartyMember> party = new ArrayList<PartyMember>();
        party.add(new PartyMember("Ebony", 1, 0, "mediumslow"));
        party.add(new PartyMember("Allie", 1, 0, "slow"));

        XPMachine xp = new XPMachine(party);

        Scanner scan = null;
        try {
            scan = new Scanner(new File("unittest"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (scan.hasNext()) {
            xp.addEncounter(new Encounter(scan.next(), scan.nextInt()));
        }


        xp.giveXP();
        System.out.println(xp);

        ArrayList<PartyMember> peeps = xp.getParty();
        for (int i = 0; i < peeps.size(); i++) {
            peeps.get(i).addXP(1000);
            System.out.println(peeps.get(i).getProgress() + " " + peeps.get(i).getLevel());
        }

    }
}
