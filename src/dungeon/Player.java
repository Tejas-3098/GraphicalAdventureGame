package dungeon;

import java.util.List;

/**
 * Player interface which contains getter methods for location ID, co-ordinates of the player,
 * setter method to set the location ID of the player, method to add treasure
 * and getter method for getting treasure.
 */
public interface Player {

  /**
   * Returns the location ID of the player.
   *
   * @return int
   */
  int getPlayerLocId();

  /**
   * Increments the arrow count of the player by 1 whenever the player picks up an arrow.
   */
  void incrementArrow();

  /**
   * Decrements the arrow count of the player by 1 whenever the player shoots an arrow.
   */
  void decrementArrow();

  /**
   * Checks if the player is alive at any given instant of the game.
   *
   * @param g the game object.
   * @return boolean
   */
  boolean isPlayerAlive(Game g);

  /**
   * Used to set the player's location ID.
   *
   * @param nextLocation the location where the player will move to.
   */
  void setPlayerLocId(int nextLocation);

  /**
   * Used to get the treasure picked by player.
   *
   * @return list of treasure.
   */
  List<TreasureI> getTreasurePicked();

  /**
   * Gets the co-ordinates of the player's current location.
   *
   * @return Location
   */
  Location getPlayerCoordinates();

  /**
   * Used to add treasure to the treasure list.
   *
   * @param treasure treasure list
   */
  void addTreasure(TreasureI treasure);

  /**
   * Used to set the co-ordinates of the player.
   *
   * @param locFromLocId Location of the player.
   */
  void setCoordinatesOfPlayer(Location locFromLocId);

  /**
   * Used to determine the number of arrows that the player has.
   *
   * @return int
   */
  int getArrowsEqipped();
}
