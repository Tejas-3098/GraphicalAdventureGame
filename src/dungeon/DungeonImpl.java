package dungeon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

/**
 * DungeonImpl class contains the most important methods of this project like
 * createZeroConnectivityDungeon, createNConnectivityDungeon, getSetOfEdgesForEachNode
 * which handles wrapping dungeon creation. It takes in the parameters given by the user and creates
 * a maze by adding random treasures, arrows and monsters that are dangerous and can
 * kill the player. It also contains various getter methods that are used to get information about
 * the created dungeon.
 */
public class DungeonImpl implements Dungeon {
  private final int interconnectivity;
  private final int rows;
  private final int columns;
  private boolean wrapping;
  private final int difficulty;
  private final Node[][] dungeon2D;
  ArrayList<ArrayList<Integer>> setOfLocations = new ArrayList<>();
  ArrayList<ArrayList<Integer>> listOfEdges;
  ArrayList<ArrayList<Integer>> listOfRemEdges = new ArrayList<>();
  ArrayList<Treasure> treasureList = new ArrayList<>();

  /**
   * Constructor for DungeonImpl which takes in 6 parameters - rows, columns, interconnectivity,
   * wrapping, percentage and difficulty which is the number of monsters.
   */
  public DungeonImpl(int rows, int columns, int interconnectivity, String wrapping, int perc, int n)
          throws IllegalArgumentException {
    if (rows < 0 || rows > 100 || columns < 0 || columns > 100 || interconnectivity < 0
            || (!wrapping.equalsIgnoreCase("YES")
            && !wrapping.equalsIgnoreCase("NO"))) {
      throw new IllegalArgumentException("Invalid values provided!");
    }
    this.rows = rows;
    this.columns = columns;
    this.interconnectivity = interconnectivity;
    if (Objects.equals(wrapping, "YES") || Objects.equals(wrapping, "Yes")
            || Objects.equals(wrapping, "yes")) {
      this.wrapping = true;
    } else if (Objects.equals(wrapping, "NO") || Objects.equals(wrapping, "No")
            || Objects.equals(wrapping, "no")) {
      this.wrapping = false;
    }
    this.difficulty = n;
    this.dungeon2D = new Node[rows][columns];
    this.listOfEdges = new ArrayList<>();
    createDungeon();
  }

  private void createDungeon() {
    //assigning location
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        Location loc = new Location(i, j);
        dungeon2D[i][j] = new Node(loc);
      }
    }

    //According to video, initializing the dungeon 2D array
    for (int i = 0; i < this.rows; i++) {
      for (int j = 0; j < this.columns; j++) {
        Location loc = dungeon2D[i][j].getLoc();
        getInitialSetOfLoc(i, j);
        getSetOfEdgesForEachNode(i, j, wrapping, loc);
      }
    }

    //Constructing grid with interconnectivity = 0
    createZeroInterconnectivityDungeon();

    //Constructing grid with interconnectivity = n
    createNconnectivityDungeon();

    //setting the location type as tunnel or cave based on no of connections
    setTypeOfEachNode();

    //Getting a count of the number of Caves
    int totalCaves = getCountOfCaves();

    //Getting a count of the number of tunnels
    int totalTunnels = getCountOfTunnels();

    //Getting first character of the possible directions

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        String shortMove = "";

        Location loc = dungeon2D[i][j].getLoc();

        //ArrayList<Moves> moves = dungeon2D[i][j].getPossibleMoves();
        ArrayList<Moves> moves = getNodesReachableFromCurrentNode(dungeon2D[loc.getX()]
                [loc.getY()].getLocId());
        ArrayList<String> m = new ArrayList<>();
        for (int r = 0; r < moves.size(); r++) {
          m.add(moves.get(r).toString());
        }
        Collections.sort(m);
        for (int s = 0; s < m.size(); s++) {
          shortMove = shortMove + m.get(s).charAt(0) + " ";
        }
        dungeon2D[i][j].setLocMove(shortMove);
      }
    }
  }

  @Override
  //helper method to count the number of caves
  public int getCountOfCaves() {
    int c = 0;
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        if (this.dungeon2D[i][j].getLocType() == LocationType.CAVE) {
          c += 1;
        }
      }
    }
    return c;
  }

  @Override
  public int getRows() {
    return this.rows;
  }

  @Override
  public int getColumns() {
    return this.columns;
  }

  @Override
  public boolean getWrappingStatus() {
    return this.wrapping;
  }

  @Override
  public ArrayList<Integer> getCaves() {
    ArrayList<Integer> listOfCaves = new ArrayList<>();
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        if (dungeon2D[i][j].getLocType() == LocationType.CAVE) {
          listOfCaves.add(dungeon2D[i][j].getLocId());
        }
      }
    }
    return listOfCaves;
  }

  @Override
  //helper method to count the number of tunnels
  public int getCountOfTunnels() {
    int c = 0;
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        if (this.dungeon2D[i][j].getLocType() == LocationType.TUNNEL) {
          c += 1;
        }
      }
    }
    return c;
  }

  private void setTypeOfEachNode() {
    int noOfConnections = 0;
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        noOfConnections = dungeon2D[i][j].getAdjacents().size();
        if (noOfConnections == 2) {
          this.dungeon2D[i][j].setLocType(LocationType.TUNNEL);
        } else {
          this.dungeon2D[i][j].setLocType(LocationType.CAVE);
        }
      }
    }
  }

  //Kruskal's algo implementation
  private void createZeroInterconnectivityDungeon() {
    while (listOfEdges.size() != 0) {
      //ArrayList<Integer> oneRandomEdge = listOfEdges.remove(0); // Generate randomly later
      int e = RandomNumberGenerator.genRan(listOfEdges.size(), 0);
      ArrayList<Integer> oneRandomEdge = listOfEdges.remove(e);
      int n1 = oneRandomEdge.get(0);
      int n2 = oneRandomEdge.get(1);

      int n1LocInLocSet = getNodePosInLocSet(n1);
      int n2LocInLocSet = getNodePosInLocSet(n2);

      // When the selected nodes are not in the same Location set
      if (n1LocInLocSet != n2LocInLocSet) {
        Location coOrdOfN1 = getLocFromLocId(n1);
        Location coOrdOfN2 = getLocFromLocId(n2);

        //Adding node 2 as adjacent of node 1
        this.dungeon2D[coOrdOfN1.getX()][coOrdOfN1.getY()].addAdjacents(n2);

        //Adding node 1 as adjacent of node 2
        this.dungeon2D[coOrdOfN2.getX()][coOrdOfN2.getY()].addAdjacents(n1);

        //Merging the 2 sets in which node 1 and node 2 are located
        this.setOfLocations.get(n1LocInLocSet).addAll(this.setOfLocations.get(n2LocInLocSet));
        this.setOfLocations.remove(this.setOfLocations.get(n2LocInLocSet));
      } else {
        //When the selected nodes are in the same location set
        this.listOfRemEdges.add(oneRandomEdge);
      }
    }
  } //Kruskal MST created with interconnectivity of 0.

  private void createNconnectivityDungeon() {
    //for loop to create a dungeon with connectivity n
    for (int i = 0; i < interconnectivity; i++) {
      //ArrayList<Integer> oneRandomEdge = listOfRemEdges.remove(0); // Generate randomly later
      int e = RandomNumberGenerator.genRan(listOfRemEdges.size(), 1);
      ArrayList<Integer> oneRandomEdge = listOfRemEdges.remove(e);
      int n1 = oneRandomEdge.get(0);
      int n2 = oneRandomEdge.get(1);

      Location coOrdOfN1 = getLocFromLocId(n1);
      Location coOrdOfN2 = getLocFromLocId(n2);

      //Adding node 2 as adjacent of node 1
      this.dungeon2D[coOrdOfN1.getX()][coOrdOfN1.getY()].addAdjacents(n2);

      //Adding node 1 as adjacent of node 2
      this.dungeon2D[coOrdOfN2.getX()][coOrdOfN2.getY()].addAdjacents(n1);
    } // This creates a dungeon with interconnectivity of n
  }

  @Override
  public Location getLocFromLocId(int locId) {
    for (int i = 0; i < this.rows; i++) {
      for (int j = 0; j < this.columns; j++) {
        if (this.dungeon2D[i][j].getLocId() == locId) {
          return this.dungeon2D[i][j].getLoc();
        }
      }
    }
    return new Location(-1, -1);
  }

  private int getNodePosInLocSet(Integer n) {
    for (int i = 0; i < setOfLocations.size(); i++) {
      for (int j = 0; j < setOfLocations.get(i).size(); j++) {
        if (n.equals(setOfLocations.get(i).get(j))) {
          return i;
        }
      }
    }
    return -1;
  }

  private void getInitialSetOfLoc(int i, int j) {
    ArrayList<Integer> locSubsetList = new ArrayList<>();
    locSubsetList.add(dungeon2D[i][j].getLocId());
    this.setOfLocations.add(locSubsetList);
  }

  /**
   * This method is used to create the possible edges list which represents the adjacents
   * that a node can be connected to.
   */
  public void getSetOfEdgesForEachNode(int i, int j, boolean wrapping, Location loc) {
    // Creating the possible edges list
    if (loc.getX() + 1 < this.rows) {
      ArrayList<Integer> subSetOfEdges = new ArrayList<>();
      subSetOfEdges.add(this.dungeon2D[i][j].getLocId());
      subSetOfEdges.add(this.dungeon2D[i + 1][j].getLocId());
      listOfEdges.add(subSetOfEdges);
    }

    if (loc.getY() + 1 < this.columns) {
      ArrayList<Integer> subSetOfEdges = new ArrayList<>();
      subSetOfEdges.add(this.dungeon2D[i][j].getLocId());
      subSetOfEdges.add(this.dungeon2D[i][j + 1].getLocId());
      listOfEdges.add(subSetOfEdges);
    }

    //If it is a wrapping dungeon then assigning the connections
    if (wrapping) {
      if (loc.getX() + 1 == this.rows) {
        ArrayList<Integer> subSetOfEdges = new ArrayList<>();
        subSetOfEdges.add(this.dungeon2D[i][j].getLocId());
        subSetOfEdges.add(this.dungeon2D[0][j].getLocId());
        listOfEdges.add(subSetOfEdges);

      }
      if (loc.getY() + 1 == this.columns) {
        ArrayList<Integer> subSetOfEdges = new ArrayList<>();
        subSetOfEdges.add(this.dungeon2D[i][j].getLocId());
        subSetOfEdges.add(this.dungeon2D[i][0].getLocId());
        listOfEdges.add(subSetOfEdges);
      }
    }
  }

  @Override
  public ArrayList<Moves> getNodesReachableFromCurrentNode(int locId) {
    Location nodeLoc = getLocFromLocId(locId);
    int x = nodeLoc.getX();
    int y = nodeLoc.getY();
    ArrayList<Integer> reachableNodes;
    reachableNodes = get2dDungeon()[x][y].getAdjacents();
    for (Integer reachableNode : reachableNodes) {
      Location adjLoc = getLocFromLocId(reachableNode);
      int adjX = adjLoc.getX();
      int adjY = adjLoc.getY();
      if ((adjX - x) == 0 && (adjY - y) == 1) {
        Moves m = new Moves(reachableNode, Directions.EAST);
        get2dDungeon()[x][y].setPossibleMoves(m);
      }
      if ((x - adjX) == 0 && (y - adjY) == 1) {
        Moves m = new Moves(reachableNode, Directions.WEST);
        get2dDungeon()[x][y].setPossibleMoves(m);
      }
      if ((adjY - y) == 0 && (adjX - x) == 1) {
        Moves m = new Moves(reachableNode, Directions.SOUTH);
        get2dDungeon()[x][y].setPossibleMoves(m);
      }
      if ((y - adjY) == 0 && (x - adjX) == 1) {
        Moves m = new Moves(reachableNode, Directions.NORTH);
        get2dDungeon()[x][y].setPossibleMoves(m);
      }
    }
    return get2dDungeon()[x][y].getPossibleMoves();
  }


  @Override
  public void assignTreasure(int perc) throws IllegalArgumentException {
    //throwing an exception if the percentage is not a valid value
    if (perc > 100 || perc < 0) {
      throw new IllegalArgumentException("Percentage should be between 0 and 100");
    }
    int totalCaves = getCountOfCaves();
    int numberOfCavesToBeAddedWithTreasure = 0;
    numberOfCavesToBeAddedWithTreasure = (int) Math.ceil(((double) (totalCaves * perc) / 100));
    assignTreasureToCaves(numberOfCavesToBeAddedWithTreasure);
  }

  private void assignTreasureToCaves(int numberOfCavesToBeAddedWithTreasure) {
    ArrayList<Integer> setOfNodes = new ArrayList<>();
    for (int i = 1; i <= rows * columns; i++) {
      setOfNodes.add(i);
    }
    int cavesAssigned = 0;
    while (cavesAssigned != numberOfCavesToBeAddedWithTreasure) {
      //int ranNode = setOfDNodes.remove(0); // Will be randomized later
      int r = RandomNumberGenerator.genRan(setOfNodes.size(), 0);
      int ranNode = setOfNodes.remove(r);
      Location locRan = getLocFromLocId(ranNode);

      if (this.dungeon2D[locRan.getX()][locRan.getY()].getLocType() == LocationType.CAVE) {
        treasureList.addAll(Arrays.asList(Treasure.values()));

        //selecting and adding 2 treasures randomly
        for (int i = 0; i < 2; i++) {
          //Treasure treasure = treasureList.remove(0); // Will pick a random value later
          int ra = RandomNumberGenerator.genRan(treasureList.size(), 0);
          Treasure treasure = treasureList.remove(ra);
          TreasureI selectedTreasure = null;
          if (treasure == Treasure.DIAMOND) {
            selectedTreasure = new Diamond();
          } else if (treasure == Treasure.RUBY) {
            selectedTreasure = new Ruby();
          } else if (treasure == Treasure.SAPPHIRE) {
            selectedTreasure = new Sapphire();
          }
          this.dungeon2D[locRan.getX()][locRan.getY()].addTreasure(selectedTreasure);
        }
        cavesAssigned++;
      } else {
        continue;
      }
    }
  }

  @Override
  public void assignMonster(int n, int startCaveLocId, int endCaveLocId)
          throws IllegalArgumentException {
    Location l = getLocFromLocId(startCaveLocId);
    dungeon2D[l.getX()][l.getY()].setVisited(startCaveLocId);
    assignMonsterToCaves(n, startCaveLocId, endCaveLocId);
  }

  private void assignMonsterToCaves(int numberOfCavesToBeAddedWithMonster,
                                    int startCaveLocId, int endCaveLocId) {
    ArrayList<Integer> setOfNodes = new ArrayList<>();
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        setOfNodes.add(dungeon2D[i][j].getLocId());
      }
    }
    //Removing start cave as monster cannot be in start cave
    //Location locStartCave = getLocFromLocId(startCaveLocId);
    //setOfNodes.remove(locStartCave);
    //setOfNodes.remove(startCaveLocId);
    //Adding monster to end cave
    Location locEndCave = getLocFromLocId(endCaveLocId);

    //Location locEndCave = getLocFromLocId(endCaveLocId);
    //setOfNodes.remove(locEndCave);
    Monster m1 = new Monster(2);
    this.dungeon2D[locEndCave.getX()][locEndCave.getY()].addMonster(m1);
    this.dungeon2D[locEndCave.getX()][locEndCave.getY()].getMonster().remove(0);
    //Now adding monsters randomly to other caves
    int cavesAssigned = 1;
    while (cavesAssigned != numberOfCavesToBeAddedWithMonster) {
      //int ranNode = setOfDNodes.remove(0); // Will be randomized later
      int r = RandomNumberGenerator.genRan(setOfNodes.size(), 0);
      int ranNode = setOfNodes.remove(r);
      Location locRan = getLocFromLocId(ranNode);

      if (this.dungeon2D[locRan.getX()][locRan.getY()].getLocId() != startCaveLocId
              && this.dungeon2D[locRan.getX()][locRan.getY()].getLocType() == LocationType.CAVE
              && this.dungeon2D[locRan.getX()][locRan.getY()].getMonster().size() == 1) {
        Monster m = new Monster(2);
        this.dungeon2D[locRan.getX()][locRan.getY()].addMonster(m);
        this.dungeon2D[locRan.getX()][locRan.getY()].getMonster().remove(0);
        cavesAssigned++;
      }
    }
  }

  @Override
  public void assignArrows(int perc) throws IllegalArgumentException {
    //throwing an exception if the percentage is not a valid value
    if (perc > 100 || perc < 0) {
      throw new IllegalArgumentException("Percentage should be between 0 and 100");
    }
    int totalNodes = getCountOfCaves() + getCountOfTunnels();
    int numberOfNodesToBeAddedWithTreasure = 0;
    numberOfNodesToBeAddedWithTreasure = (int) Math.ceil(((double) (totalNodes * perc) / 100));
    //System.out.println("\n" + numberOfCavesToBeAddedWithTreasure
    //+ " random caves have been added with Treasure!"); // comment it out later
    assignArrowsToNodes(numberOfNodesToBeAddedWithTreasure);
  }

  private void assignArrowsToNodes(int numberOfNodesToBeAddedWithArrows) {
    ArrayList<Integer> setOfNodes = new ArrayList<>();
    for (int i = 1; i <= rows * columns; i++) {
      setOfNodes.add(i);
    }
    int nodesAssigned = 0;

    while (nodesAssigned != numberOfNodesToBeAddedWithArrows) {
      //int ranNode = setOfDNodes.remove(0); // Will be randomized later
      int r = RandomNumberGenerator.genRan(setOfNodes.size(), 0);
      int ranNode = setOfNodes.remove(r);
      Location locRan = getLocFromLocId(ranNode);
      this.dungeon2D[locRan.getX()][locRan.getY()].updateArrow(true);
      nodesAssigned++;
    }
    //System.out.println("Arrows have been assigned to " + nodesAssigned + " random nodes");
  }

  /*
  @Override
  public int getArrowCount() {
    return this.arrowCount;
  }*/

  /*
  @Override
  public void decrementArrowCount() {
    this.arrowCount -= 1;
  }*/

  @Override
  public boolean isMonsterPresent(int locId) {
    Location l = getLocFromLocId(locId);
    int x = l.getX();
    int y = l.getY();
    if (dungeon2D[x][y].getLocType() == LocationType.TUNNEL) {
      return false;
    } else {
      return dungeon2D[x][y].getLocType() == LocationType.CAVE
              && dungeon2D[x][y].getMonster().get(0) != null;
    }
  }

  @Override
  public Node[][] get2dDungeon() {
    return this.dungeon2D;
  }

  @Override
  public ArrayList<Treasure> getTreasure() {
    return treasureList;
  }

  @Override
  public int getNumberOfCavesWithTreasure() {
    int c = 0;
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        if (!dungeon2D[i][j].getTreasure().isEmpty()) {
          c += 1;
        }
      }
    }
    return c;
  }

  @Override
  public int getNumberOfMonsters() {
    return this.difficulty;
  }

  @Override
  public String toString() {
    String d = "";
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        d = d + dungeon2D[i][j].toString() + " ";
      }
      d = d + "\n";
    }
    return d;
  }

}
