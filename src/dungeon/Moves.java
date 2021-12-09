package dungeon;

/**
 * Moves class contains a move constructor which contains the location Id of each node
 * and the directions that the player can move to from each node and also contains getter functions
 * for locId and moves from current node.
 */
public class Moves {
  private final int locId;
  private final Directions movesForCurrentNode;

  public Moves(int locId, Directions m) {
    this.locId = locId;
    this.movesForCurrentNode = m;
  }

  public int getLocId() {
    return this.locId;
  }

  public Directions getMovesForCurrentNode() {
    return this.movesForCurrentNode;
  }

  @Override
  public String toString() {
    return String.valueOf(movesForCurrentNode) + " ";
  }
}
