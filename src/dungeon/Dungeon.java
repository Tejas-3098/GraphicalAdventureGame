package dungeon;

import java.util.ArrayList;

/**
 * Dungeon interface which contains getter methods location, location ID, dungeon, caves, tunnels
 * and treasure.
 */
public interface Dungeon {

  Location getLocFromLocId(int locId);

  ArrayList<Moves> getNodesReachableFromCurrentNode(int locId);

  void assignTreasure(int perc);

  void assignMonster(int perc, int startGameLocId, int endCaveLocId)
          throws IllegalArgumentException;

  void assignArrows(int perc) throws IllegalArgumentException;

  //int getArrowCount();

  //void decrementArrowCount();

  boolean isMonsterPresent(int locId);

  Node[][] get2dDungeon();

  int getCountOfCaves();

  int getCountOfTunnels();

  int getRows();

  int getColumns();

  boolean getWrappingStatus();

  ArrayList<Integer> getCaves();

  ArrayList<Treasure> getTreasure();

  int getNumberOfCavesWithTreasure();

  int getNumberOfMonsters();
}
