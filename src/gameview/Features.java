package gameview;

import java.awt.event.KeyEvent;

/**
 * This interface represents a set of features that the program offers. Each
 * feature is exposed as a function in this interface. This function is used
 * suitably as a callback by the view, to pass control to the controller. How
 * the view uses them as callbacks is completely up to how the view is designed
 * (e.g. it could use them as a callback for a button, or a callback for a
 * dialog box, or a set of text inputs, etc.)
 *
 * <p>Each function is designed to take in the necessary data to complete that
 * functionality.
 */
public interface Features {

  /**
   * When a move related key is pressed, this method is called which moves the player
   * according to the key pressed.
   *
   * @param k the key which is pressed.
   */
  void move(KeyEvent k);

  /**
   * When a pickup related key is pressed, this method is called and the player picks up
   * either treasure or arrow depending on which key is pressed.
   *
   * @param k the key which is pressed.
   */
  void pickup(KeyEvent k);

  /**
   * When a shoot key is pressed, the player shoots an arrow in the specified direction and
   * distance.
   *
   * @param e the key pressed.
   * @param d the distance.
   */
  void shoot(KeyEvent e, int d);

  /**
   * Used to restart the game with the same parameters.
   *
   * @param text rows
   * @param columnsText columns
   * @param interconnectivityText interconnectivity
   * @param wrappingText wrapping
   * @param percentageText percentage of caves to be added with treasure
   * @param numOfMonstersText number of monsters
   */
  void restart(String text, String columnsText, String interconnectivityText,
               String wrappingText, String percentageText, String numOfMonstersText);

  /**
   * Used to clear the values of the input parameters.
   */
  void clear();

  /**
   * Used to start the game with the given parameters.
   *
   * @param text rows
   * @param text1 columns
   * @param text2 interconnectivity
   * @param text3 wrapping
   * @param text4 percentage of caves to be added with treasure
   * @param text5 number of monsters
   */
  void startGame(String text, String text1, String text2, String text3, String text4, String text5);

  /**
   * This method ends the game and closes the window and displays an exit code 0.
   */
  void quitGame();

  /**
   * This method is used to create a new game.
   */
  void newGame();
}
