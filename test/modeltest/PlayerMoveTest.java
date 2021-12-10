
package modeltest;

import org.junit.Before;
import org.junit.Test;

import dungeon.Game;
import dungeon.GameI;

import static org.junit.Assert.assertEquals;

/**
 * JUnit test class which contains a test to check if the Player can move in all 4 directions.
 */
public class PlayerMoveTest {
  GameI g;

  @Before
  public void setUp() throws Exception {
    g = new Game(5, 5, 8, "yes", 85, 10);
  }

  @Test
  //test to check if player can move in all 4 directions
  public void testIfPlayerCanMoveAll4() {
    int c = 0;
    g.startGame();
    g.assignGameTreasure(100);
    g.setStartAndEndCaveToTestIt(8, 9);
    g.movePlayerNorth();
    c += 1;
    g.movePlayerEast();
    c += 1;
    g.movePlayerWest();
    c += 1;
    g.movePlayerSouth();
    c += 1;
    assertEquals(4, c);
  }
}


