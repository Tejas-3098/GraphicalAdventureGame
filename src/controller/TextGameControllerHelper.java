package controller;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

import dungeon.Directions;
import dungeon.Game;

/**
 * Helper class for the controller which contains the helper methods to move the player,
 * pick up treasure and arrows and shoot arrows.
 */
public class TextGameControllerHelper {

  private final Appendable out;

  /**
   * Constructor for the helper class which takes in Readable and Appendable as parameters
   * which are first null validated.
   */
  public TextGameControllerHelper(Readable in, Appendable out) {

    if (in == null || out == null) {
      throw new IllegalArgumentException("Readable and Appendable can't be null");
    }
    this.out = out;
    Scanner t = new Scanner(in);
  }

  void move(String d, Game g) throws IOException {
    if (Objects.equals(d, "N")) {
      g.movePlayerNorth();
      if (!g.getPlayer().isPlayerAlive(g)) {
        out.append("Chomp Chomp Chomp, you are eaten by an Otyugh!\n" + "Better luck next time\n");
      }
      if (g.getPlayer().getPlayerLocId() == g.getEndCaveLocId()) {
        out.append("Congrats! Player has won the game!");
      }
    } else if (Objects.equals(d, "S")) {
      g.movePlayerSouth();
      if (!g.getPlayer().isPlayerAlive(g)) {
        out.append("Chomp Chomp Chomp, you are eaten by an Otyugh!\n" + "Better luck next time\n");
      }
      if (g.getPlayer().getPlayerLocId() == g.getEndCaveLocId()) {
        out.append("Congrats! Player has won the game!");
      }
    } else if (Objects.equals(d, "W")) {
      g.movePlayerWest();
      if (!g.getPlayer().isPlayerAlive(g)) {
        out.append("Chomp Chomp Chomp, you are eaten by an Otyugh!\n" + "Better luck next time\n");
      }
      if (g.getPlayer().getPlayerLocId() == g.getEndCaveLocId()) {
        out.append("Congrats! Player has won the game!");
      }
    } else if (Objects.equals(d, "E")) {
      g.movePlayerEast();
      if (!g.getPlayer().isPlayerAlive(g)) {
        out.append("Chomp Chomp Chomp, you are eaten by an Otyugh!\n" + "Better luck next time");
      }
      if (g.getPlayer().getPlayerLocId() == g.getEndCaveLocId()) {
        out.append("Congrats! Player has won the game!");
      }
    }
  }

  void pickup(String p, Game g) {
    if (Objects.equals(p, "Treasure") || Objects.equals(p, "treasure")) {
      g.collectTreasure();

    }
    if (Objects.equals(p, "Arrow") || Objects.equals(p, "arrow")) {
      g.pickUpArrow();
    }
  }

  String shootArrow(String p, int d, Game g) {
    StringBuilder s = new StringBuilder();
    if (Objects.equals(p, "N")) {
      s.append(g.shootArrow(Directions.NORTH, d));
    } else if (Objects.equals(p, "S")) {
      s.append(g.shootArrow(Directions.SOUTH, d));
    } else if (Objects.equals(p, "W")) {
      s.append(g.shootArrow(Directions.WEST, d));
    } else if (Objects.equals(p, "E")) {
      s.append(g.shootArrow(Directions.EAST, d));
    }
    return s.toString();
  }
}
