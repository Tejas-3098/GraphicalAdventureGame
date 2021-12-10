package modeltest;

import org.junit.Test;

import dungeon.Dungeon;
import dungeon.DungeonImpl;

/**
 * Test class for DungeonImpl to check if any invalid values of the parameters are passed
 * to the Constructor.
 */
public class DungeonImplTestInvalid {

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidRows() {
    Dungeon d = new DungeonImpl(-5, 6, 8, "Yes", 85, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidColumns() {
    Dungeon d = new DungeonImpl(5, -3, 8, "No", 85, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidInterconnectivity() {
    Dungeon d = new DungeonImpl(5, 6, -28, "no", 85, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidWrapping() {
    Dungeon d = new DungeonImpl(5, 6, 8, "100", 85, 10);
  }
}