package dungeon;

import java.util.ArrayList;

import gameview.ReadOnlyDungeonModel;

/**
 * Game interface which contains methods related to Dungeon and Player both like createGameDungeon,
 * startGame, assignGameTreasure, movePlayer, getPlayer, collectTreasure.
 */
public interface GameI extends ReadOnlyDungeonModel {

  void printDungeonGame(int rows, int columns, GameI g);

  Node[][] createGameDungeon(int rows, int columns, int interconnectivity, String wrapping,
                             int perc, int n);

  Node[][] restartGame(int rows, int columns, int interconnectivity,
                       String wrapping, int perc, int n);

  void setRows(String s);

  void assignGameTreasure(int perc);

  void assignMonsterToGame(int perc, int startCaveLocId, int endCaveLocId);

  Node[][] getGame2dDungeon();

  ArrayList<Moves> getReachableNodes(int locId);

  void startGame();

  String movePlayerEast();

  String movePlayerWest();

  String movePlayerNorth();

  String movePlayerSouth();

  void setStartAndEndCaveToTestIt(int s, int e);

  int getStartCaveLocId();

  int getEndCaveLocId();

  Player getPlayer();

  boolean getMonsterStatus(int locId);

  int getSmell();

  void assignArrowsToGame(int perc);

  String collectTreasure();

  String shootArrow(Directions d, int dist);

  ArrayList<Treasure> getGameTreasure();

  Location getPlayLocFromLocId(int locId);

  void pickUpArrow();

  boolean isGameOver();

  int getRows();

  int getColumns();

  void setCols(String text);

  void setInt(String s);

  void setWrapping(String s);

  void setPerc(String s);

  void setNumOfMon(String s);
}
