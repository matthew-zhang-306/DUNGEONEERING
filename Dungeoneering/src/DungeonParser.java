import java.util.*;
import java.io.*;

/**
 * DungeonParser
 *
 * Converts dungeon text into usable data.
 *
 * v. 1.1
 */


public class DungeonParser {


    /**********
     * THINGS *
     **********/

    String dungeon;
    int floors;
    int roomsMin;
    int roomsMax;
    int trapsMax;
    int[] trapChances;
    int trapsNum;
    ChanceObject[] trapTypes;
    int monsterHouseChance;
    int bigRoomChance;
    int pokemonNum;
    String[] pokemon;
    int levelMin;
    int levelMax;
    int itemsMax;
    int[] itemChances;
    int itemsNum;
    ChanceObject[] itemTypes;
    int pokeMin;
    int pokeMax;

    public DungeonParser(String d) {
        dungeon = d;

        parse();
    }


    /**************
     * MAIN PARSE *
     **************/

    public void parse() {
        // initialize scanner
        Scanner scan = null;
        try {
            scan = new Scanner(new File("dg_" + dungeon));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // parse text
        int[] temp;

        scan.nextLine();
        floors = parseSingleInt(8,scan);

        temp = parseRange(17, scan);
        roomsMin = temp[0];
        roomsMax = temp[1];

        trapsMax = parseSingleInt(7,scan);
        trapChances = parseChanceList(trapsMax, 12, scan);
        trapsNum = parseSingleInt(11, scan);
        trapTypes = parseChanceObjects(trapsNum, scan);

        monsterHouseChance = parseSingleInt(15, scan);
        bigRoomChance = parseSingleInt(17, scan);

        pokemonNum = parseSingleInt(17, scan);
        pokemon = scan.nextLine().split(", ");
        pokemon[0] = pokemon[0].trim();

        temp = parseRange(13, scan);
        levelMin = temp[0];
        levelMax = temp[1];

        itemsMax = parseSingleInt(7,scan);
        itemChances = parseChanceList(itemsMax, 12, scan);
        itemsNum = parseSingleInt(11, scan);
        itemTypes = parseChanceObjects(itemsNum, scan);

        temp = parseRange(12, scan);
        pokeMin = temp[0];
        pokeMax = temp[1];

    }


    /*****************
     * PARSE METHODS *
     *****************/

    public int parseSingleInt(int index, Scanner scan) {
        return Integer.parseInt(scan.nextLine().substring(index));
    }

    public int[] parseRange(int index, Scanner scan) {
        String[] range = scan.nextLine().substring(index).split("-");
        int[] numbers = {Integer.parseInt(range[0]), Integer.parseInt(range[1])};
        return numbers;
    }

    public int[] parseChanceList(int size, int index, Scanner scan) {
        int[] output = new int[size];
        for (int a = 0; a < size; a++) {
            output[a] = Integer.parseInt(scan.nextLine().substring(index));
        }
        return output;
    }

    public ChanceObject[] parseChanceObjects(int size, Scanner scan) {
        ChanceObject[] output = new ChanceObject[size];
        for (int b = 0; b < size; b++) {
            String[] data = scan.nextLine().split("-");
            int l = Integer.parseInt(data[0].trim());
            int h = Integer.parseInt(data[1]);
            String n = data[2];
            output[b] = new ChanceObject(l, h, n);
        }
        return output;
    }


    /***************
     * GET METHODS *
     ***************/

    public int getFloors() {
        return floors;
    }
    public int getRoomsMin() { return roomsMin; }
    public int getRoomsMax() { return roomsMax; }

    public int getTrapsMax() { return trapsMax; }
    public int getTrapChance(int num) {
        if (num > trapChances.length) return 0;
        return trapChances[num-1];
    }
    public int getTrapsNum() { return trapsNum; }
    public ChanceObject getTrapType(int num) {
        if (num > trapTypes.length) return null;
        return trapTypes[num-1];
    }

    public int getMonsterHouseChance() { return monsterHouseChance; }
    public int getBigRoomChance() { return bigRoomChance; }

    public int getPokemonNum() { return pokemonNum; }
    public String getPokemon(int num) {
        if (num > pokemon.length) return "";
        return pokemon[num-1];
    }
    public int getLevelMin() { return levelMin; }
    public int getLevelMax() { return levelMax; }

    public int getItemsMax() { return itemsMax; }
    public int getItemChance(int num) {
        if (num > itemChances.length) return 0;
        return itemChances[num-1];
    }
    public int getItemsNum() { return itemsNum; }
    public ChanceObject getItemType(int num) {
        if (num > itemTypes.length) return null;
        return itemTypes[num-1];
    }

    public int getPokeMin() { return pokeMin; }
    public int getPokeMax() { return pokeMax; }

}
