import java.util.*;

public class Room {

    DungeonParser dungeon;
    Random random;
    boolean big;
    boolean monsterhouse;
    int itemNum = 0;
    int trapNum = 0;
    int encounterNum = 1;
    Pickup[] items;
    Pickup[] traps;
    Encounter[] encounters;

    public Room(DungeonParser d, boolean b, boolean m) {
        dungeon = d;
        big = b;
        monsterhouse = m;
        random = new Random();
    }

    public void populate() {
        int max;

        /************
         * ITEM GEN *
         ************/
        max = dungeon.getItemsMax();
        int itemroll = random.nextInt(100);
        for (int i = max; i > 0; i--) {
            if (itemroll < dungeon.getItemChance(i)) {
                itemNum = i;
                break;
            }
        }
        if (monsterhouse)
            itemNum += 3;
        if (big)
            itemNum *= 2;

        items = new Pickup[itemNum];
        for (int i = 0; i < itemNum; i++) {
            itemroll = random.nextInt(100);
            for (int a = 1; a <= dungeon.getItemsNum(); a++) {
                if (dungeon.getItemType(a).getInRange(itemroll)) {
                    items[i] = new Pickup(dungeon, dungeon.getItemType(a).getName());
                    break;
                }
            }

        }

        /************
         * TRAP GEN *
         ************/
        max = dungeon.getItemsMax();
        int traproll = random.nextInt(100);
        for (int t = max; t > 0; t--) {
            if (traproll < dungeon.getTrapChance(t)) {
                trapNum = t;
                break;
            }
        }
        if (big)
            trapNum *= 2;

        traps = new Pickup[trapNum];
        for (int t = 0; t < trapNum; t++) {
            traproll = random.nextInt(100);
            for (int a = 1; a <= dungeon.getTrapsNum(); a++) {
                if (dungeon.getTrapType(a).getInRange(itemroll)) {
                    traps[t] = new Pickup(dungeon, dungeon.getTrapType(a).getName());
                    break;
                }
            }

        }

        /*****************
         * ENCOUNTER GEN *
         *****************/
        if (monsterhouse)
            encounterNum = random.nextInt(5) + 6;
        else if (random.nextInt(100) < 10)
            encounterNum++;
        if (big)
            encounterNum *= 2;

        encounters = new Encounter[encounterNum];
        for (int e = 0; e < encounterNum; e++) {
            int encounterroll = random.nextInt(dungeon.getPokemonNum()) + 1;
            int range = dungeon.getLevelMax() - dungeon.getLevelMin() + 1;
            int levelroll = random.nextInt(range) + dungeon.getLevelMin();

            encounters[e] = new Encounter(dungeon, dungeon.getPokemon(encounterroll), levelroll);
        }
    }


    /*****************
     * ARRAY RETURNS *
     *****************/
    public Pickup[] getItems() {
        return items;
    }
    public Pickup[] getTraps() {
        return traps;
    }
    public Encounter[] getEncounters() {
        return encounters;
    }


    public String toString() {

        String output = "Room  ||  ITEMS" + printItems() + "\n" +
                        "      ||  TRAPS" + printTraps() + "\n" +
                        "      ||  ENCOUNTERS" + printEncounters();

        if (big || monsterhouse) {
            output += "\n" + "      ||  ";
            if (big) output += ">>  BIG  ";
            if (monsterhouse) output += ">>  M.HOUSE  ";
        }

        return output;

    }

    /****************
     * ARRAY PRINTS *
     ****************/
    public String printItems() {
        String print = "[";
        for (int a = 0; a < itemNum; a++) {
            print += items[a];
            if (a != itemNum - 1)
                print += ", ";
        }
        return print + "]";
    }
    public String printTraps() {
        String print = "[";
        for (int a = 0; a < trapNum; a++) {
            print += traps[a];
            if (a != trapNum - 1)
                print += ", ";
        }
        return print + "]";
    }
    public String printEncounters() {
        String print = "[";
        for (int a = 0; a < encounterNum; a++) {
            print += encounters[a];
            if (a != encounterNum - 1)
                print += ", ";
        }
        return print + "]";
    }



}
