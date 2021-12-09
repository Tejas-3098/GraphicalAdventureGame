package dungeon;

/**
 * Treasure interface which contains the public facing code, methods like getTypeOfTreasure
 * and getWeight.
 */
public interface TreasureI {

  Treasure getTypeOfTreasure();

  int getWeight();
}
