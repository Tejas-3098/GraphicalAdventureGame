package gameview;

import java.awt.event.ActionListener;

import controller.GuiGameConsoleController;

/**
 * The view interface for the dungeon. To motivate the methods here
 * think about all the operations that the controller
 * would need to invoke on the view.
 */
public interface dungeonI {

  String popup();

  void resetFocus();

  /**
   * Make the view visible. This is usually called
   * after the view is constructed
   */
  void makeVisible();

  /**
   * Provide the view with an action listener for
   * the button that should cause the program to
   * process a command. This is so that when the button
   * is pressed, control goes to the action listener
   * @param actionEvent
   */
  void setCommandButtonListener(ActionListener actionEvent);

  /**
   * Signal the view to draw itself
   */
  void refresh();

  void addClickListener(GuiGameConsoleController guiGameConsoleController);

  void setFeatures(Features f);

  void clearInputParams();

  void showResult(String monster);
}
