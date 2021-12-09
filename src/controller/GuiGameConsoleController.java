package controller;

import java.awt.event.KeyEvent;

import dungeon.Directions;
import dungeon.Game;
import gameview.Features;
import gameview.dungeonI;

public class GuiGameConsoleController implements GuiGameController, Features {
  private final Game model;
  private dungeonI view;

  public GuiGameConsoleController(Game model, dungeonI view) {
    if (model == null || view == null) {
      throw new IllegalArgumentException("Invalid arguments to the controller!");
    }
    this.model = model;
    this.view = view;
  }

  @Override
  /**
   * Mutator for the view.
   *
   * @param v the view to use
   */
  public void setView(dungeonI v) {
    this.view = v;
    // give the feature callbacks to the view
    view.setFeatures(this);
  }

  @Override
  public void exitProgram() {
    System.exit(0);
  }

  @Override
  public void move(KeyEvent k) {
    if(!model.isGameOver()) {
      if (k.getKeyCode() == KeyEvent.VK_UP) {
        System.out.println("Player moved North!");
        model.movePlayerNorth();

      } else if (k.getKeyCode() == KeyEvent.VK_DOWN) {
        model.movePlayerSouth();

        System.out.println("Player moved S!");

      } else if (k.getKeyCode() == KeyEvent.VK_LEFT) {
        model.movePlayerWest();

        System.out.println("Player moved W!");
      } else if (k.getKeyCode() == KeyEvent.VK_RIGHT) {
        model.movePlayerEast();

        System.out.println("Player moved E!");
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
    if(k.getKeyCode() == 'P') {
      model.pickUpArrow();
      view.refresh();
    }
    if(k.getKeyCode() == 'T') {
     model.collectTreasure();
     view.refresh();
    }
  }

  @Override
  public void shoot(KeyEvent k, int d) {
    String s = "";
    if(k.getKeyCode() == 'W') {
      s = model.shootArrow(Directions.NORTH,d);
      System.out.println("Arrow shot towards N!");
      System.out.println(s);
      view.refresh();
    }
    else if(k.getKeyCode() == 'S') {
      s = model.shootArrow(Directions.SOUTH,d);
      System.out.println("Arrow shot towards S!");
      System.out.println(s);
      view.refresh();
    }
    else if(k.getKeyCode() == 'A') {
      s = model.shootArrow(Directions.WEST,d);
      System.out.println("Arrow shot towards W!");
      System.out.println(s);
      view.refresh();
    }
    else if(k.getKeyCode() == 'D') {
      s = model.shootArrow(Directions.EAST,d);
      System.out.println("Arrow shot towards E!");
      System.out.println(s);
      view.refresh();
    }
  }

  @Override
  public void restart(String t1, String t2, String t3, String t4, String t5, String t6) {
    model.restartGame(Integer.parseInt(t1),Integer.parseInt(t2), Integer.parseInt(t3),
            t4, Integer.parseInt(t5), Integer.parseInt(t6));
    view.refresh();
    view.makeVisible();
  }

  @Override
  public void processRows(String text) {
    model.setRows(text);
  }

  @Override
  public void processCols(String text) {
    model.setCols(text);
  }

  @Override
  public void processInt(String text) {
    model.setInt(text);
  }

  @Override
  public void processWrap(String text) {
    model.setWrapping(text);
  }

  @Override
  public void processPerc(String text) {
    model.setPerc(text);
  }

  @Override
  public void processNumOfMons(String text) {
    model.setNumOfMon(text);
  }

  @Override
  public void startGame(String t1, String t2, String t3, String t4, String t5, String t6) {
    model.restartGame(Integer.parseInt(t1),Integer.parseInt(t2), Integer.parseInt(t3),
            t4, Integer.parseInt(t5), Integer.parseInt(t6));
    view.refresh();
    view.makeVisible();
  }

  @Override
  public void quitGame() {
    System.exit(0);
  }

  @Override
  public void clear() {
    view.clearInputParams();
  }

  @Override
  public void playGame(Game model) throws IllegalArgumentException {
    while(!model.isGameOver()) {
      view.addClickListener(this);

    }

  }

}
