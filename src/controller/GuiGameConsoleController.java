package controller;

import dungeon.Directions;
import dungeon.Game;
import gameview.DungeonI;
import gameview.Features;
import java.awt.event.KeyEvent;
import java.util.Objects;

/**
 * GuiGameConsoleController class represents the Graphical game controller which has methods
 * to set the view which is displayed, move the player in the view, pick up treasure and arrows and
 * shoot arrows to kill the monsters present in the game.
 */
public class GuiGameConsoleController implements GuiGameController, Features {
  private final Game model;
  private DungeonI view;

  /**
   * Constructor for the controller which takes in the model and the view and initializes them
   * so that the game can start.
   */
  public GuiGameConsoleController(Game model, DungeonI view) {
    if (model == null || view == null) {
      throw new IllegalArgumentException("Invalid arguments to the controller!");
    }
    this.model = model;
    this.view = view;
  }

  /**
   * Mutator for the view.
   *
   * @param v the view to use
   */
  @Override
  public void setView(DungeonI v) {
    this.view = v;
    // give the feature callbacks to the view
    view.setFeatures(this);
  }

  @Override
  public void move(KeyEvent k) {
    if (!model.isGameOver()) {
      if (k.getKeyCode() == KeyEvent.VK_UP) {
        //System.out.println("Player moved North!");
        model.movePlayerNorth();

      } else if (k.getKeyCode() == KeyEvent.VK_DOWN) {
        model.movePlayerSouth();
        //System.out.println("Player moved S!");

      } else if (k.getKeyCode() == KeyEvent.VK_LEFT) {
        model.movePlayerWest();
        //System.out.println("Player moved W!");
      } else if (k.getKeyCode() == KeyEvent.VK_RIGHT) {
        model.movePlayerEast();
        //System.out.println("Player moved E!");
      }
      if (!model.getPlayer().isPlayerAlive(model)) {
        view.showResult("monster");
      }
      if (model.getPlayer().getPlayerLocId() == model.getEndCaveLocId()
              && model.getPlayer().isPlayerAlive(model)) {
        view.showResult("player");
      }
      view.refresh();
    }
  }

  @Override
  public void pickup(KeyEvent k) {
    if (k.getKeyCode() == 'P') {
      model.pickUpArrow();
      view.refresh();
    }
    if (k.getKeyCode() == 'T') {
      model.collectTreasure();
      view.refresh();
    }
  }

  @Override
  public void shoot(KeyEvent k, int d) {
    String s = "";
    if (k.getKeyCode() == 'W') {
      s = model.shootArrow(Directions.NORTH, d);
      System.out.println("Arrow shot towards N!");
      System.out.println(s);
    } else if (k.getKeyCode() == 'S') {
      s = model.shootArrow(Directions.SOUTH, d);
      System.out.println("Arrow shot towards S!");
      System.out.println(s);
    } else if (k.getKeyCode() == 'A') {
      s = model.shootArrow(Directions.WEST, d);
      System.out.println("Arrow shot towards W!");
      System.out.println(s);
    } else if (k.getKeyCode() == 'D') {
      s = model.shootArrow(Directions.EAST, d);
      System.out.println("Arrow shot towards E!");
      System.out.println(s);
    }
    if (Objects.equals(s, "Monster injured")) {
      view.showResult("injured");
    } else if (Objects.equals(s, "Monster killed")) {
      view.showResult("killed");
    } else if (Objects.equals(s, "Player has missed the shot")) {
      view.showResult("missed");
    } else if (Objects.equals(s, "Invalid move!")) {
      view.showResult("no path");
    }

  }

  @Override
  public void restart(String t1, String t2, String t3, String t4, String t5, String t6) {
    model.restartGame(Integer.parseInt(t1), Integer.parseInt(t2), Integer.parseInt(t3),
            t4, Integer.parseInt(t5), Integer.parseInt(t6));
    view.refresh();
    view.makeVisible();
  }

  @Override
  public void startGame(String t1, String t2, String t3, String t4, String t5, String t6) {
    model.restartGame(Integer.parseInt(t1), Integer.parseInt(t2), Integer.parseInt(t3),
            t4, Integer.parseInt(t5), Integer.parseInt(t6));
    view.refresh();
    view.makeVisible();
  }

  @Override
  public void quitGame() {
    System.exit(0);
  }

  @Override
  public void newGame() {
    view.makeNonVisible();
  }

  @Override
  public void clear() {
    view.clearInputParams();
  }

  @Override
  public void playGame(Game model) throws IllegalArgumentException {
    while (!model.isGameOver()) {
      view.addClickListener(this);
    }
  }
}
