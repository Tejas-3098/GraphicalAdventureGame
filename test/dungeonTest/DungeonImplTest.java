package dungeonTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import dungeon.Dungeon;
import dungeon.DungeonImpl;
import dungeon.Location;
import dungeon.Node;
import dungeon.RandomNumberGenerator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test for DungeonImpl Class which contains Junit tests for the basic operations
 * involving the dungeon.
 */
public class DungeonImplTest {
  Dungeon d;
  Dungeon d1;


  @Before
  public void setUp() throws Exception {
    d = new DungeonImpl(6, 7, 8, "no", 85, 10);
    d1 = new DungeonImpl(5, 5, 8, "yes", 85, 10);
  }

  //mock random number gen
  @Test
  public void testGenerationOfRandomNumbers() {
    int r = RandomNumberGenerator.genRan(1, 1);
    assertEquals(1, r);
  }

  @Test
  public void testGetCountOfCaves() {
    assertTrue(d.getCountOfCaves() > 0 && d.getCountOfCaves() < 30);
  }

  @Test
  public void testGetCountOfTunnels() {
    assertTrue(d.getCountOfTunnels() > 0 && d.getCountOfTunnels() < 30);
  }

  //test for non-wrapping dungeon
  @Test
  public void testForNonWrappingDungeon() {
    Node[][] de;
    de = d.get2dDungeon();
    assertEquals(42, d.getCountOfCaves() + d.getCountOfTunnels());
  }

  //test that verifies treasure is added to current percentage of caves
  @Test
  public void testTreasureAdded() {

    int perc = 90;
    d.assignTreasure(90);
    int c = d.getCountOfCaves();
    //System.out.println(c);
    int a = (int) Math.ceil(((double) (c * perc) / 100));
    assertEquals(a, d.getNumberOfCavesWithTreasure());
  }

  @Test
  public void testOtyughAtEndCave() {
    d.assignMonster(85, 6,1);
    Location l = d.getLocFromLocId(1);
    Assert.assertFalse(d.get2dDungeon()[l.getX()][l.getY()].getMonster().isEmpty());
  }

  @Test
  public void testOtyughNotAtStartCave() {
    d.assignMonster(85, 6,1);
    Location l = d.getLocFromLocId(6);
    Assert.assertTrue(d.get2dDungeon()[l.getX()][l.getY()].getMonster().isEmpty());
  }



}