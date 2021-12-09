package dungeon;

/**
 * Class for the type of treasure Sapphire, it extends the abstract treasure class and
 * contains methods like getTypeOfTreasure and getWeight.
 */
public class Sapphire extends AbstractTreasure {
  public Sapphire() {
    super(Treasure.SAPPHIRE, 5);
  }

  @Override
  public Treasure getTypeOfTreasure() {
    return this.typeOfTreasure;
  }

  @Override
  public int getWeight() {
    return this.weight;
  }
}