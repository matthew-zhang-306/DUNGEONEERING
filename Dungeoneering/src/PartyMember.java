import java.util.*;
import java.io.*;

public class PartyMember {

    String name;
    String xpspeed;
    int level;
    int xp;

    public PartyMember(String n) {
        name = n;

        getStats();
    }

    public void getStats() {
        Scanner scan = null;
        try {
            scan = new Scanner(new File("rpers"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String[] stats = null;
        while (scan.hasNext()) {
            stats = scan.nextLine().split(" ");
            if (stats[0].equals(name)) break;
        }
        level = Integer.parseInt(stats[1]);
        xp = Integer.parseInt(stats[2]);
        xpspeed = stats[3];

    }

    public String getName() { return name; }
    public int getLevel() { return level; }
    public int getXP() { return xp; }
    public String getXPspeed() { return xpspeed; }


    /*************
     * ADDING XP *
     *************/
    public void addXP(int a) {
        xp += a;

        // GET LEVEL
        for (int i = level; i < 100; i++) {
            if (levelReq(xpspeed, i) > xp) {
                level = i-1;
                break;
            }
        }
    }

    public int levelReq(String speed, int level) {
        int output = (int)Math.pow(level, 3);
        if (speed.equals("fast")) {
            output *= 4/5;
        }
        else if (speed.equals("slow")) {
            output *= 5/4;
        }
        else if (speed.equals("mediumslow")) {
            double temp = output * 6.0 / 5.0;
            output = (int)(temp - (15 * (int)Math.pow(level, 2)) + (100 * level) - 140);
        }

        return output;
    }

}
