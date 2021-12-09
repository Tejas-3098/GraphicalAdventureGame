package dungeon;

import java.util.ArrayList;

/**
 * Class which represents one node of the dungeon and has methods for adding adjacents of each node,
 * getting Location of each node, it's co-ordinates, setting the location type, getting the
 * adjacent nodes and adding and getting the treasure assigned to each node.
 */
public class Node {
  private static int ID = 0;
  private final int locId;
  private final Location loc;
  private final ArrayList<Integer> adjacents;
  private final ArrayList<Moves> possibleMoves;
  private LocationType locType;
  private final ArrayList<TreasureI> treasure;
  private final ArrayList<Monster> monster;
  boolean arrow;
  private String possMoves;
  Boolean visited;

  /**
   * Constructor for DNode which takes in the parameter of location of the node in the dungeon.
   */
  public Node(Location loc) {
    this.locId = ++ID;
    this.loc = loc;
    this.adjacents = new ArrayList<>();
    this.locType = LocationType.CAVE;
    this.treasure = new ArrayList<>();
    this.possibleMoves = new ArrayList<>();
    this.monster = new ArrayList<>();
    monster.add(null);
    this.arrow = false;
    this.possMoves = "";
    this.visited = false;

  }

  /**
   * Given the location ID, this method makes adjacent nodes of that given node as its adjacents.
   */
  public void addAdjacents(int locId) {
    this.adjacents.add(locId);
  }

  /**
   * Returns the possible moves list which contains possible moves from each node.
   */
  public ArrayList<Moves> getPossibleMoves() {
    return this.possibleMoves;
  }

  /**
   * Sets the visited parameter as true if the node is already visited.
   */
  public void setVisited(int locId) {
    this.visited = true;
  }

  /**
   * Returns the visited status for each node, true if the node has already been visited
   * and false if it has not been visited yet.
   */
  public Boolean getVisitedStatus() {
    return this.visited;
  }

  /**
   * Adds the move if it is possible to the list of possible moves.
   */
  public void setPossibleMoves(Moves m) {
    this.possibleMoves.add(m);
  }

  /**
   * Returns the co-ordinates of a node in the dungeon.
   */
  public Location getLoc() {
    return this.loc;
  }

  /**
   * Returns the location ID of a node in the dungeon.
   */
  public int getLocId() {
    return this.locId;
  }

  /**
   * Returns the adjacent nodes list of the given node.
   */
  public ArrayList<Integer> getAdjacents() {
    return this.adjacents;
  }

  /**
   * Sets the location type of node as CAVE or TUNNEL depending on the number of entrances.
   */
  public void setLocType(LocationType e) {
    this.locType = e;
  }

  /**
   * Sets the moves possible from that location.
   */
  public void setLocMove(String s) {
    this.possMoves = s;
  }

  public String getLocMove() {
    return this.possMoves;
  }

  /**
   * Returns the location type of node in the dungeon.
   */
  public LocationType getLocType() {
    return this.locType;
  }

  /**
   * Adds the selected treasure to the treasure arraylist.
   */
  public void addTreasure(TreasureI selectedTreasure) {
    this.treasure.add(selectedTreasure);
  }

  /**
   * Returns the treasure list.
   */
  public ArrayList<TreasureI> getTreasure() {
    return treasure;
  }

  /**
   * Removes i from the treasure list.
   */
  public void removeTreasure(int i) {
    treasure.remove(i);
  }

  @Override
  public String toString() {
    return "locId=" + locId
            + ", adjacents=" + adjacents
            + ", Location Type=" + locType
            + ", treasure=" + treasureToString()
            + ", Monster: " + monsterToString();
  }

  private ArrayList<String> treasureToString() {
    ArrayList<String> treasureDis = new ArrayList<>();
    if (treasure.isEmpty()) {
      return treasureDis;
    }
    for (int i = 0; i < treasure.size(); i++) {
      if (treasure.get(i).getTypeOfTreasure() == Treasure.DIAMOND) {
        treasureDis.add("DIAMOND");
      } else if (treasure.get(i).getTypeOfTreasure() == Treasure.RUBY) {
        treasureDis.add("RUBY");
      }
      if (treasure.get(i).getTypeOfTreasure() == Treasure.DIAMOND) {
        treasureDis.add("SAPPHIRE");
      }
    }
    return treasureDis;
  }

  public void updateArrow(boolean arrow) {
    this.arrow = arrow;
  }

  public boolean getArrowStatus() {
    return this.arrow;
  }

  public void addMonster(Monster m) {
    monster.add(m);
  }

  public ArrayList<Monster> getMonster() {
    return this.monster;
  }


  private String monsterToString() {
    if (monster.isEmpty()) {
      return "NOT PRESENT! Safe Location!";
    }
    if (monster.get(0) != null) {
      return "PRESENT! Danger!!";
    } else {
      return "Tunnel! NO Monsters here!!";
    }
  }
}
