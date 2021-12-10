package controller;

import java.awt.event.KeyEvent;

import dungeon.Game;
import gameview.DungeonI;

/**
 * This interface represents the controller for the game and has methods for setting the view
 * and the playGame method where the game is actually played until either monster eats the player
 * or the player wins by killing the monster.
 */
public interface GuiGameController {
  /**
   * Mutator for the view.
   *
   * @param v the view to use
   */
  void setView(DungeonI v);

  /**
   * The playGame method is an important method where the game is actually played until either
   * monster eats the player or the player wins by killing the monster. The controller controls
   * each action and tells the view to update itself according to the changes that have been made
   * in the model.
   */
  void playGame(Game model) throws IllegalArgumentException;

  /**
   * When a move related key is pressed, this method is called which moves the player
   * according to the key pressed.
   */
  void move(KeyEvent k);

  /** When a pickup related key is pressed, this method is called and the player picks up
   * either treasure or arrow depending on which key is pressed.
   */
  void pickup(KeyEvent k);

  /** When a shoot key is pressed, the player shoots an arrow in the specified direction and
   * distance.
   */
  void shoot(KeyEvent k, int d);
}
