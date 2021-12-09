package dungeon;

import java.util.ArrayList;

/**
 * Dungeon interface which contains getter methods location, location ID, dungeon, caves, tunnels
 * and treasure.
 */
public interface Dungeon {

  /**
   * Method to get the location of a dungeon node given its location ID.
   */
  Location getLocFromLocId(int locId);

  /**
   * Method to get the location of a dungeon node given its location ID.
   */
  ArrayList<Moves> getNodesReachableFromCurrentNode(int locId);

  /**
   * Method to assign treasure to the caves given the percentage of caves to be added with treasure.
   */
  void assignTreasure(int perc);

  /**
   * Method to assign monsters to the caves.
   */
  void assignMonster(int perc, int startGameLocId, int endCaveLocId)
          throws IllegalArgumentException;

  /**
   * Method to assign arrows to the dungeon.
   */
  void assignArrows(int perc) throws IllegalArgumentException;

  /**
   * Checks whether a monster is present at the given location.
   */
  boolean isMonsterPresent(int locId);

  /**
   * Method to get the whole 2d dungeon.
   */
  Node[][] get2dDungeon();

  /**
   * Method to get the count of caves in the dungeon.
   */
  int getCountOfCaves();

  /**
   * Method to get the count of tunnels in the dungeon.
   */
  int getCountOfTunnels();

  /**
   * Method to get the number of rows in the dungeon.
   */
  int getRows();

  /**
   * Method to get the number of columns in the dungeon.
   */
  int getColumns();

  /**
   * Method to check if the dungeon is wrapping or not.
   */
  boolean getWrappingStatus();

  /**
   * Method to get the list of caves in the dungeon.
   */
  ArrayList<Integer> getCaves();

  /**
   * Method to get the list of treasure in each node in the dungeon.
   */
  ArrayList<Treasure> getTreasure();

  /**
   * Method to get the number of caves with treasure in the dungeon.
   */
  int getNumberOfCavesWithTreasure();

  /**
   * Method to get the number of monsters in the dungeon.
   */
  int getNumberOfMonsters();
}
