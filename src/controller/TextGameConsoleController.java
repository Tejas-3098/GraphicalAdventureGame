package controller;

import dungeon.Game;
import dungeon.Location;
import dungeon.LocationType;
import dungeon.Moves;
import dungeon.Treasure;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

/**
 * Class which represents the text based controller for the game. Each action taken by the model
 * for example move, pickup and shoot is observed by the controller and the controller displays the
 * appropriate text message depending on the result of the action taken.
 */
public class TextGameConsoleController implements TextGameController {

  private final Readable in;
  private final Appendable out;
  private TextGameControllerHelper help;

  /**
   * Constructor for the controller.
   *
   * @param in  the source to read from
   * @param out the target to print to
   */
  public TextGameConsoleController(Readable in, Appendable out) {
    if (in == null || out == null) {
      throw new IllegalArgumentException("Readable and Appendable can't be null");
    }
    this.out = out;
    Scanner t = new Scanner(in);
    this.in = in;
  }

  @Override
  public void playGame(Game g) throws IllegalArgumentException {

    if (g == null) {
      throw new IllegalArgumentException("Game model is null.");
    }
    Scanner t = new Scanner(in);
    help = new TextGameControllerHelper(in, out);
    //while(!g.isGameOver()) {
    try {
      boolean flag = true;
      do {
        out.append(getNodeDescription(g, flag));

        flag = movePickShoot(g);
        //}}
      }
      while (!g.isGameOver());
    } catch (IOException ioe) {
      throw new IllegalStateException("Append Failed!", ioe);
    }
  }

  private boolean movePickShoot(Game g) throws IOException {
    Scanner t = new Scanner(in);
    out.append("\n\nMove, Pickup, or Shoot (M-P-S)?");
    String ch = getUserChoice(g);
    switch (ch) {
      case "M":
        out.append("Where? ");
        String d = t.next();
        help.move(d, g);
        int lId = g.getPlayer().getPlayerLocId();
        if ((g.getPlayer().getPlayerLocId() != g.getEndCaveLocId())) {
          out.append("Player moved to node ").append(String.valueOf(lId)).append("\n");
        }
        return true;
      case "P":
        out.append("What? ");
        String p = t.next();
        help.pickup(p, g);
        if (Objects.equals(p, "Treasure") || Objects.equals(p, "treasure")) {
          out.append("You picked up the treasure in the cave!\n");
        }
        if (Objects.equals(p, "Arrow") || Objects.equals(p, "arrow")) {
          out.append("You picked up an arrow!\n");
        }
        break;
      case "S":
        out.append("No. of caves (1-5)? ");
        int nc = t.nextInt();
        out.append("Where to? ");
        String sh = t.next();
        String res = help.shootArrow(sh, nc, g);
        out.append(res + "\n");
        break;
      case "Q":

      default:
        out.append("Enter one of M,P or S");
    }
    return false;
  }

  private String getNodeDescription(Game g, boolean flag) {
    StringBuilder s = new StringBuilder();

    int r = g.getSmell();
    System.out.println(Arrays.deepToString(g.getGame2dDungeon()));
    if (r == 1) {
      s.append("\nYou smell something terrible nearby");
    }
    if (r == 2) {
      System.out.println("\nYou get a faint smell of something terrible");
    }
    int pLocId = g.getPlayer().getPlayerLocId();
    Location pLoc = g.getPlayLocFromLocId(pLocId);
    LocationType l = g.getGame2dDungeon()[pLoc.getX()][pLoc.getY()].getLocType();
    if (l == LocationType.CAVE) {
      s.append("\nYou are in a ").append(l);
    } else {
      s.append("\nYou are in a ").append(l);
    }
    if (!g.getGame2dDungeon()[pLoc.getX()][pLoc.getY()].getTreasure().isEmpty()) {
      s.append("\nYou find ");
      for (int i = 0; i < g.getGame2dDungeon()[pLoc.getX()]
              [pLoc.getY()].getTreasure().size(); i++) {
        Treasure t = g.getGameTreasure().get(i);
        if (t == Treasure.DIAMOND) {
          s.append("1 DIAMOND ");
        }
        if (t == Treasure.RUBY) {
          s.append("1 RUBY ");
        }
        if (t == Treasure.SAPPHIRE) {
          s.append("1 SAPPHIRE ");
        }
      }
      s.append(" and 1 arrow here");
    }

    if (flag) {
      g.getReachableNodes(pLocId);
    }
    ArrayList<Moves> m;
    m = g.getGame2dDungeon()[pLoc.getX()][pLoc.getY()].getPossibleMoves();

    s.append("\nDoors lead to ");
    for (int i = 0; i < m.size(); i++) {
      s.append(m.get(i));
    }

    return s.toString();
  }

  private String getUserChoice(Game g) {
    StringBuilder s = new StringBuilder();
    int pLocId = g.getPlayer().getPlayerLocId();
    Location pLoc = g.getPlayLocFromLocId(pLocId);
    LocationType l = g.getGame2dDungeon()[pLoc.getX()][pLoc.getY()].getLocType();
    Scanner t = new Scanner(in);
    String ch = t.next();
    return ch;
  }

}


