package controller;

import dungeon.Game;

/**
 * Interface for the game controller which contains playGame method. The playGame method is an
 * important method where the game is actually played until either monster eats the player
 * or the player wins by killing the monster. The controller controls each action and tells the
 * view to update itself according to the changes that have been made in the model.
 */
public interface TextGameController {

  /**
   * The playGame method checks after each action whether player has won the game or lost it, it is
   * a method where the game is played until either monster eats the player or the player wins
   * by killing the monster.
   */
  void playGame(Game g) throws IllegalArgumentException;

}
