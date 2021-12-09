package dungeon;

/**
 * Class for the type of treasure diamond which also contains a constructor for diamond.
 */
public class Diamond extends AbstractTreasure {
  /**
   * Constructor for diamond.
   */
  public Diamond() {
    super(Treasure.DIAMOND, 15);
  }

  @Override
  public Treasure getTypeOfTreasure() {
    return this.typeOfTreasure;
  }

}
