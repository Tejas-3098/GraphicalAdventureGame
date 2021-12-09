package controller;

import java.awt.event.KeyEvent;

import dungeon.Game;
import gameview.dungeonI;

public interface GuiGameController {
  /**
   * Mutator for the view.
   *
   * @param v the view to use
   */
  void setView(dungeonI v);

  void exitProgram();

  void move(KeyEvent k);

  void pickup(KeyEvent k);

  void playGame(Game model) throws IllegalArgumentException;
}
