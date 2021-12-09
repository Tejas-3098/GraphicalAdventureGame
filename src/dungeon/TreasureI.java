package dungeon;

/**
 * Treasure interface which contains the public facing code, methods like getTypeOfTreasure
 * and getWeight.
 */
public interface TreasureI {

  /**
   * Returns the type of the treasure that is present at that location.
   */
  Treasure getTypeOfTreasure();

}
