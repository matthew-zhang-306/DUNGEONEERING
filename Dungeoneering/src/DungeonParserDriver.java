/**
 * <h1>DungeonParserDriver</h1>
 * The DungeonParserDriver class tests the functionality of the DungeonParser class.
 *
 * @author  myName
 * @version 1.0
 */

public class DungeonParserDriver {

    public static void main(String args[]) {

        DungeonParser dungeon = new DungeonParser("moonywoods");

        System.out.println(dungeon.getFloors());
        System.out.println(dungeon.getRoomsMin());
        System.out.println(dungeon.getRoomsMax());
        System.out.println(dungeon.getTrapsMax());
        System.out.println(dungeon.getTrapChance(1));
        System.out.println(dungeon.getTrapChance(2));
        System.out.println(dungeon.getTrapsNum());
        System.out.println(dungeon.getTrapType(1));
        System.out.println(dungeon.getTrapType(2));
        System.out.println(dungeon.getMonsterHouseChance());
        System.out.println(dungeon.getBigRoomChance());
        System.out.println(dungeon.getPokemonNum());
        System.out.println(dungeon.getPokemon(1));
        System.out.println(dungeon.getPokemon(4));
        System.out.println(dungeon.getLevelMin());
        System.out.println(dungeon.getLevelMax());
        System.out.println(dungeon.getItemsMax());
        System.out.println(dungeon.getItemChance(1));
        System.out.println(dungeon.getItemChance(2));
        System.out.println(dungeon.getItemsNum());
        System.out.println(dungeon.getItemType(1));
        System.out.println(dungeon.getItemType(2));
        System.out.println(dungeon.getPokeMin());
        System.out.println(dungeon.getPokeMax());
    }

}
