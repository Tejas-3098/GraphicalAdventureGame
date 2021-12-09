package controllertest;

import org.junit.Assert;
import org.junit.Test;

import java.io.StringReader;
import java.util.ArrayList;

import controller.TextGameConsoleController;
import controller.TextGameController;
import dungeon.Game;
import dungeon.Location;
import dungeon.Monster;

/**
 * A class to test the working of the controller extensively.
 */
public class TextGameConsoleControllerTest {

  @Test(expected = IllegalStateException.class)
  public void testFailingAppendable() {
    // Testing when something goes wrong with the Appendable
    // Here we are passing it a mock of an Appendable that always fails
    Game g = new Game(5, 5, 8, "no", 50, 10);
    StringReader input = new StringReader("M P S");
    Appendable gameLog = new FailingAppendable();
    TextGameConsoleController t = new TextGameConsoleController(input, gameLog);
    t.playGame(g);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidModel() {
    Game g = new Game(5, 8, 12, "no", 45, 15);
    StringReader input = null;
    Appendable gameLog = null;

    TextGameConsoleController t = new TextGameConsoleController(input, gameLog);
    t.playGame(g);
  }

  @Test
  public void testPickupTreasure() {
    Game g = new Game(5, 5, 8, "no", 80, 12);
    StringReader input = new StringReader("M S P Treasure M N M N");
    Appendable gameLog = new StringBuilder();
    TextGameController t = new TextGameConsoleController(input, gameLog);
    t.playGame(g);
    String s = "You picked up the treasure in the cave!";
    Assert.assertTrue(gameLog.toString().contains(s));
  }

  @Test
  public void testPickupArrow() {
    Game g = new Game(5, 5, 8, "no", 80, 12);
    StringReader input = new StringReader("M S P Arrow M N M N");
    Appendable gameLog = new StringBuilder();
    TextGameController t = new TextGameConsoleController(input, gameLog);
    t.playGame(g);
    String s = "You picked up the treasure in the cave!";
    Assert.assertTrue(gameLog.toString().contains(s));
  }

  @Test
  public void testMonsterAtEndCave() {
    Game g = new Game(5, 5, 8, "no", 80, 12);
    StringReader input = new StringReader("M S P Arrow M N M N");
    Appendable gameLog = new StringBuilder();
    TextGameController t = new TextGameConsoleController(input, gameLog);
    t.playGame(g);
    int id = g.getEndCaveLocId();
    Location locToCheck = g.getPlayLocFromLocId(id);
    ArrayList<Monster> m = g.getGame2dDungeon()[locToCheck.getX()][locToCheck.getY()].getMonster();
    Assert.assertFalse(m.isEmpty());
  }

  @Test
  public void testInvalidMove() {
    Game g = new Game(5, 5, 8, "no", 80, 12);
    StringReader input = new StringReader("M N M W");
    Appendable gameLog = new StringBuilder();
    TextGameController t = new TextGameConsoleController(input, gameLog);
    t.playGame(g);
    String s = "Player cannot move north as there is no path towards north.\"\n" +
            "            + \" Please choose some other direction!";
    Assert.assertTrue(gameLog.toString().contains(s));
  }

  @Test
  public void testIfShootArrowMisses() {
    Game g = new Game(5, 5, 8, "no", 80, 12);
    StringReader input = new StringReader("M N P Arrow S 2 E");
    Appendable gameLog = new StringBuilder();
    TextGameController t = new TextGameConsoleController(input, gameLog);
    t.playGame(g);
    String s = "Player has missed the shot";
    Assert.assertTrue(gameLog.toString().contains(s));
  }

  @Test
  public void testPickUpTreasureWhenNoTreasure() {
    Game g = new Game(5, 5, 8, "no", 80, 12);
    StringReader input = new StringReader("M N P Arrow P Treasure P Treasure");
    Appendable gameLog = new StringBuilder();
    TextGameController t = new TextGameConsoleController(input, gameLog);
    t.playGame(g);
    String s = "Treasure cannot be picked as there is no treasure present at the node!";
    Assert.assertTrue(gameLog.toString().contains(s));
  }

  @Test
  public void testGetSmell() {
    Game g = new Game(5, 5, 8, "no", 80, 12);
    StringReader input = new StringReader("M N P Arrow S 2 E");
    Appendable gameLog = new StringBuilder();
    TextGameController t = new TextGameConsoleController(input, gameLog);
    t.playGame(g);
    String s1 = "You smell something terrible nearby";
    String s2 = "You get a faint smell of something terrible";
    Assert.assertTrue(gameLog.toString().contains(s1));
    Assert.assertTrue(gameLog.toString().contains(s2));
  }

  @Test
  public void testOtyughInjuredAndKilled() {
    Game g = new Game(5, 5, 8, "no", 80, 12);
    StringReader input = new StringReader("M N P Arrow S 2 E S 2 E");
    Appendable gameLog = new StringBuilder();
    TextGameController t = new TextGameConsoleController(input, gameLog);
    t.playGame(g);
    String s1 = "Monster injured";
    String s2 = "Monster killed";
    Assert.assertTrue(gameLog.toString().contains(s1));
    Assert.assertTrue(gameLog.toString().contains(s2));
  }

  @Test
  public void testPlayerWins() {
    Game g = new Game(5, 5, 8, "yes", 85, 10);
    StringReader input = new StringReader("S 1 N S 1 N M N");
    Appendable gameLog = new StringBuilder();
    TextGameController t = new TextGameConsoleController(input, gameLog);
    t.playGame(g);
    String s = "Congrats! Player has won the game!";
    Assert.assertTrue(gameLog.toString().contains(s));
  }

  @Test
  public void testValidMove() {
    Game g = new Game(5, 5, 8, "no", 80, 12);
    StringReader input = new StringReader("S 1 N S 1 N M N");
    Appendable gameLog = new StringBuilder();
    TextGameController t = new TextGameConsoleController(input, gameLog);
    t.playGame(g);
    String s = "Player moved to node 1";
    Assert.assertTrue(gameLog.toString().contains(s));
  }

  @Test
  public void testInitialArrowCount() {
    Game g = new Game(5, 5, 8, "no", 80, 12);
    StringReader input = new StringReader("M N");
    Appendable gameLog = new StringBuilder();
    TextGameController t = new TextGameConsoleController(input, gameLog);
    int res = g.getPlayer().getArrowsEqipped();
    Assert.assertEquals(3, res);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testZeroDistanceToShootArrow() {
    Game g = new Game(5, 5, 8, "no", 80, 12);
    StringReader input = new StringReader("M N S 0 E");
    Appendable gameLog = new StringBuilder();
    TextGameController t = new TextGameConsoleController(input, gameLog);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegDistanceToShootArrow() {
    Game g = new Game(5, 5, 8, "no", 80, 12);
    StringReader input = new StringReader("M N M S S -20 W");
    Appendable gameLog = new StringBuilder();
    TextGameController t = new TextGameConsoleController(input, gameLog);
  }

  @Test
  public void testMonsterEatsPlayer() {
    Game g = new Game(5, 5, 8, "no", 80, 12);
    StringReader input = new StringReader("M S M S M E");
    Appendable gameLog = new StringBuilder();
    TextGameController t = new TextGameConsoleController(input, gameLog);
    t.playGame(g);
    String s = "Chomp Chomp Chomp, you are eaten by an Otyugh!";
    Assert.assertTrue(gameLog.toString().contains(s));
  }

}