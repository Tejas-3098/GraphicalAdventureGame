import java.io.IOException;
import java.io.InputStreamReader;

import controller.GuiGameConsoleController;
import controller.GuiGameController;
import controller.TextGameConsoleController;
import dungeon.Game;
import gameview.dungeonI;
import gameview.dungeonView;

/**
 * Driver class which contains the main method.
 */
public class Driver {

  /**
   * Main method which initializes the controller.
   */
  public static void main(String[] x) throws IOException {
    String wrap = "";
    if (x.length == 0) {
      Game model = new Game(5,5,8,"no",80,10);
      dungeonI view = new dungeonView(model);
      GuiGameController cont = new GuiGameConsoleController(model, view);
      cont.setView(view);
      cont.playGame(model);
    }
    else if (x.length == 6) {
      System.out.println("Going in!");
      if (!isNumber(x[0]) || !isNumber(x[1]) || !isNumber(x[2])
              || (!x[3].equalsIgnoreCase("Yes")
              && !x[3].equalsIgnoreCase("no")) || !isNumber(x[4]) || !isNumber(x[5])) {
        throw new IOException("Invalid values entered!");
      }
      if (x[3].equalsIgnoreCase("Yes")) {
        wrap = "Yes";
      }
      if (x[3].equalsIgnoreCase("No")) {
        wrap = "No";
      }
      Readable input = new InputStreamReader(System.in);
      Appendable output = System.out;
      new TextGameConsoleController(input, output).playGame(new Game(Integer.parseInt(x[0]),
              Integer.parseInt(x[1]), Integer.parseInt(x[2]),x[3], Integer.parseInt(x[4]),
              Integer.parseInt(x[5])));
    }



  }

  /**
   * Checks if the entered command line argument is an integer.
   */
  public static boolean isNumber(String s) {
    int intVal;
    try {
      intVal = Integer.parseInt(s);
      return true;
    } catch (NumberFormatException n) {
      System.out.println("Please enter an integer value!");
    }
    return false;
  }
}
