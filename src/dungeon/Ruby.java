package dungeon;

/**
 * Class for the type of treasure Ruby, it extends the abstract treasure class and contains methods
 * like getTypeOfTreasure and getWeight.
 */
public class Ruby extends AbstractTreasure {
  public Ruby() {
    super(Treasure.RUBY, 10);
  }

  @Override
  public Treasure getTypeOfTreasure() {
    return this.typeOfTreasure;
  }

}
