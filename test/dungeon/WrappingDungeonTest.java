package dungeon;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test class for Wrapping Dungeon which contains Junit tests related to wrapping dungeon
 * and also some tests related to player, his location and description.
 */
public class WrappingDungeonTest {
  GameI g;

  @Before
  public void setUp() throws Exception {
    g = new Game(5, 5, 8, "yes", 85, 10);
  }

  @Test
  public void testForWrappingDungeon() {
    //g = new Game(5,5,8,"yes");
    g.startGame();
    g.setStartAndEndCaveToTestIt(5, 1);

    //test to check if player starts at the starting location.
    int c1 = g.getStartCaveLocId();
    int a1 = g.getPlayer().getPlayerLocId();
    assertEquals(c1, a1);

    //test to check wrapping dungeon
    int a = g.getPlayer().getPlayerLocId();
    g.movePlayerNorth();
    assertEquals(5, g.getPlayer().getPlayerLocId());

    //g.movePlayerNorth();
    //g.movePlayerNorth();
    //test to check if player can reach the ending location.
    int e = g.getEndCaveLocId();
    int a2 = g.getPlayer().getPlayerLocId();
    assertEquals(5, a2);

    //Test to check the player's description
    assertEquals("Location ID = 5 Treasure Collected -> DIAMOND:0 RUBY:0 SAPPHIRE:0 "
            , g.getPlayer().toString());

    //Test to check the player's location in the dungeon
    assertEquals("locId=5, adjacents=[4, 10, 1], Location Type=CAVE," +
                    " treasure=[DIAMOND, SAPPHIRE, RUBY], Monster: NOT PRESENT! Safe Location!"
            , g.getGame2dDungeon()[g.getPlayer().getPlayerCoordinates().getX()]
                    [g.getPlayer().getPlayerCoordinates().getY()].toString());

    //test to check if player can pick up treasure correctly
    g.assignGameTreasure(100);
    System.out.println(g.getGame2dDungeon()[g.getPlayer().getPlayerCoordinates().getX()]
            [g.getPlayer().getPlayerCoordinates().getY()].toString());
    assertEquals("Successfully collected the treasure!", g.collectTreasure());
  }

}
