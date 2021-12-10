package dungeon;

import gameview.ReadOnlyDungeonModel;
import java.util.List;

/**
 * Game interface which contains methods related to Dungeon and Player both like createGameDungeon,
 * startGame, assignGameTreasure, movePlayer, getPlayer, collectTreasure.
 */
public interface GameI extends ReadOnlyDungeonModel {

  /**
   * Used to print the dungeon to the screen for debugging purpose in text based game.
   *
   * @param rows the number of rows in the dungeon.
   * @param columns the number of columns in the dungeon.
   * @param g The game object.
   */
  void printDungeonGame(int rows, int columns, GameI g);

  /**
   * This method creates the dungeon.
   *
   * @param rows the number of rows in the dungeon.
   * @param columns the number of columns in the dungeon.
   * @param interconnectivity the interconnectivity of the dungeon.
   * @param wrapping boolean variable which determines whether dungeon is wrapping or not.
   * @param perc the percentage of caves to be added with treasure.
   * @param n the number of monsters.
   * @return the created dungeon.
   */
  Node[][] createGameDungeon(int rows, int columns, int interconnectivity, String wrapping,
                             int perc, int n);

  /**
   * This method is used to restart the game with the same parameters.
   *
   * @param rows the number of rows in the dungeon.
   * @param columns the number of columns in the dungeon.
   * @param interconnectivity the interconnectivity of the dungeon.
   * @param wrapping boolean variable which determines whether dungeon is wrapping or not.
   * @param perc the percentage of caves to be added with treasure.
   * @param n the number of monsters.
   * @return the created dungeon.
   */
  Node[][] restartGame(int rows, int columns, int interconnectivity,
                       String wrapping, int perc, int n);

  /**
   * This method is used to set the number of rows.
   */
  void setRows(String s);

  /**
   * Used to assign treasure to the caves.
   *
   * @param perc the percentage of caves to be assigned with treasure.
   */
  void assignGameTreasure(int perc);

  /**
   * This method assigns monsters to the game.
   *
   * @param perc the percentage of caves to be assigned with treasure.
   * @param startCaveLocId the cave from where player starts the game.
   * @param endCaveLocId the cave where the game ends.
   */
  void assignMonsterToGame(int perc, int startCaveLocId, int endCaveLocId);

  /**
   * Returns the 2d dungeon.
   */
  Node[][] getGame2dDungeon();

  /**
   * Nodes reachable from current location are returned.
   *
   * @param locId Location ID of the current location.
   * @return List of moves.
   */
  List<Moves> getReachableNodes(int locId);

  /**
   * This method initializes the game by creating the player.
   */
  void startGame();

  /**
   * Moves the player 1 node east.
   *
   * @return the outcome of moving the player.
   */
  String movePlayerEast();

  /**
   * Moves the player 1 node west.
   *
   * @return the outcome of moving the player.
   */
  String movePlayerWest();

  /**
   * Moves the player 1 node north.
   *
   * @return the outcome of moving the player.
   */
  String movePlayerNorth();

  /**
   * Moves the player 1 node south.
   *
   * @return the outcome of moving the player.
   */
  String movePlayerSouth();

  /**
   * Setting start and end caves to test the model.
   *
   * @param s the start cave
   * @param e the end cave
   */
  void setStartAndEndCaveToTestIt(int s, int e);

  /**
   * This method is a getter method which returns the location ID of the start cave.
   *
   * @return location ID of the start cave.
   */
  int getStartCaveLocId();

  /**
   * This method is a getter method which returns the location ID of the end cave.
   *
   * @return location ID of the end cave.
   */
  int getEndCaveLocId();

  /**
   * This method is used to obtain the player object.
   *
   * @return Player object
   */
  Player getPlayer();

  /**
   * This method is a getter method which returns whether a monster is present at the given
   * location or not.
   *
   * @return boolean value.
   */
  boolean getMonsterStatus(int locId);

  /**
   * This method is used to get the smell at any given location.
   *
   * @return int value which represents smell intensity.
   */
  int getSmell();

  /**
   * Used to assign arrows to the game.
   *
   * @param perc the percentage of nodes to be added with arrows
   */
  void assignArrowsToGame(int perc);

  /**
   * Used to collect the treasure present at the player's current location.
   *
   * @return the outcome of the treasure collect method call.
   */
  String collectTreasure();

  /**
   * This method is used to shoot the arrow.
   *
   * @param d Direction in which the arrow needs to be shot.
   * @param dist Distance in which the arrow needs to be shot.
   *
   * @return The outcome of the shoot arrow method call, whether monster got injured,
   *         killed or the player has missed the shot
   */
  String shootArrow(Directions d, int dist);

  /**
   * Used to call the getTreasure method in the dungeon.
   *
   * @return the list of treasure.
   */
  List<Treasure> getGameTreasure();

  /**
   * Used to get the Location of the player from the location ID.
   *
   * @param locId the location ID of the player's current location.
   * @return location of the player.
   */
  Location getPlayLocFromLocId(int locId);

  /**
   * This method is used to pick up the arrows in the game.
   */
  void pickUpArrow();

  /**
   * This method is used to check if the game is over or not.
   *
   * @return boolean value.
   */
  boolean isGameOver();

  /**
   * Used to get the number of rows in the dungeon.
   *
   * @return Integer number of rows in the dungeon.
   */
  int getRows();

  /**
   * Used to get the number of columns in the dungeon.
   *
   * @return Integer number of columns in the dungeon.
   */
  int getColumns();

  /**
   * Sets the number of columns in the dungeon.
   */
  void setCols(String text);

  /**
   * Sets the interconnectivity value.
   */
  void setInt(String s);

  /**
   * Sets the wrapping status.
   */
  void setWrapping(String s);

  /**
   * Sets the percentage of caves to be added with treasure in the dungeon.
   */
  void setPerc(String s);

  /**
   * Sets the number of monsters in the dungeon.
   */
  void setNumOfMon(String s);
}
