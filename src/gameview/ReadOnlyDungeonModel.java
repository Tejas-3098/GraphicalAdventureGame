package gameview;

import dungeon.Location;
import dungeon.Node;
import dungeon.Player;
import dungeon.Treasure;
import java.util.List;

/**
 * The interface needed for a read-only model for the Dungeon game model.
 */
public interface ReadOnlyDungeonModel {

  /**
   * Returns the 2d dungeon.
   */
  Node[][] getGame2dDungeon();

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
}
