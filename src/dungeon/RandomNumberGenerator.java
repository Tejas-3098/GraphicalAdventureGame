package dungeon;

/**
 * A class that has method that generates random numbers.
 */
public class RandomNumberGenerator {
  /**
   * Random number generator function which generates the random number by subtracting min from max
   * and multiplying with a number between 0 and 1 which is obtained by using math.random() method.
   */
  public static int genRan(int max, int min) {
    return min + (int) (Math.random() * (max - min));
  }
}
