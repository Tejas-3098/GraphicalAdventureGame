package dungeon;

/**
 * A class that has method that generates random numbers.
 */
public class RandomNumberGenerator {
  /**
   * Random number generator function.
   */
  public static int genRan(int max, int min) {
    return min + (int) (Math.random() * (max - min));
  }
}
