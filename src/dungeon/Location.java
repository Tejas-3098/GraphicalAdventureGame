package dungeon;

/**
 * Location class is used to represent the location of the dungeon nodes and contains methods
 * to get the x and y co-ordinates of a node.
 */
public class Location {

  private final int xcoord;
  private final int ycoord;

  Location(int x, int y) {
    this.xcoord = x;
    this.ycoord = y;
  }

  public int getX() {
    return xcoord;
  }

  public int getY() {
    return ycoord;
  }


}
