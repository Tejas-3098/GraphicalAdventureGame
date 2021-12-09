package gameview;

import controller.GuiGameConsoleController;

/**
 * The view interface for the dungeon. To motivate the methods here
 * think about all the operations that the controller
 * would need to invoke on the view.
 */
public interface DungeonI {

  /**
   * Used to create the input parameter screen.
   */
  void createInpParaScreen();

  /**
   * Used to show the pop-up dialog after a move, pickup or shoot action.
   *
   * @return String - the message to display.
   */
  String popDialog();


  /**
   * Make the view visible. This is usually called
   * after the view is constructed
   */
  void makeVisible();

  /**
   * Make the view non-visible. This is usually called
   * after the view is constructed
   */
  void makeNonVisible();

  /**
   * Signal the view to draw itself.
   */
  void refresh();

  /**
   * Click listener which calls a specific function when a click event happens.
   */
  void addClickListener(GuiGameConsoleController guiGameConsoleController);

  /**
   * Links all the callbacks with the listeners.
   *
   * @param f feature.
   */
  void setFeatures(Features f);

  /**
   * Method which clears the input parameters of the dungeon creation so that the user can enter
   * them again if they want to.
   */
  void clearInputParams();

  /**
   * Method which shows the result depending on the outcome of the action.
   *
   * @param s string passed by the action.
   */
  void showResult(String s);
}
