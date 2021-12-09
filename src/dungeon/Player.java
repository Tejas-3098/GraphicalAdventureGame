package dungeon;

import java.util.ArrayList;

/**
 * Player interface which contains getter methods for location ID, co-ordinates of the player,
 * setter method to set the location ID of the player, method to add treasure
 * and getter method for getting treasure.
 */
public interface Player {

  int getPlayerLocId();

  void incrementArrow();

  void decrementArrow();

  boolean isPlayerAlive(Game g);

  void setPlayerLocId(int nextLocation);

  ArrayList<TreasureI> getTreasurePicked();

  Location getPlayerCoordinates();

  void addTreasure(TreasureI treasure);

  void setCoordinatesOfPlayer(Location locFromLocId);

  int getArrowsEqipped();
}
