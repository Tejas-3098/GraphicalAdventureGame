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
   * Exit the program.
   */
  void exitProgram();

  void move(KeyEvent k);

  void pickup(KeyEvent k);

  void shoot(KeyEvent e, int d);

  void restart(String text, String columnsText, String interconnectivityText, String wrappingText, String percentageText, String numOfMonstersText);

  void processRows(String text);

  void processCols(String text);

  void processInt(String text);

  void processWrap(String text);

  void processPerc(String text);

  void processNumOfMons(String text);

  void clear();

  void startGame(String text, String text1, String text2, String text3, String text4, String text5);

  void quitGame();

  void newGame();
}
