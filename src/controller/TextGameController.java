package controller;

import dungeon.Game;

/**
 * Interface for the game controller which contains playGame method declaration.
 */
public interface TextGameController {

  void playGame(Game g) throws IllegalArgumentException;

}
