package controller;

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
}
