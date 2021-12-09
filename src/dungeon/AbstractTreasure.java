package dungeon;

/**
 * An abstract class for treasure which contains implementation of getting the type of treasure
 * and the weight of the treasure.
 */
public abstract class AbstractTreasure implements TreasureI {

  protected Treasure typeOfTreasure;
  protected int weight;

  /**
   * Constructor for abstract treasure class which takes in type of treasure and weight of each
   * treasure as parameters.
   */
  public AbstractTreasure(Treasure typeOfTreasure, int w) {
    this.typeOfTreasure = typeOfTreasure;
    this.weight = w;
  }

  @Override
  public Treasure getTypeOfTreasure() {
    return this.typeOfTreasure;
  }
}

