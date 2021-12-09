package dungeon;

import java.util.ArrayList;

/**
 * The game class is where the game is initialized, the dungeon is created and the player
 * starts making his moves from start cave to end cave.
 */
public class Game implements GameI {

  private Dungeon dungeon2D;
  private Player play;
  private int startCaveLocId;
  private Location startCaveCoordinates;
  private int endCaveLocId;
  private Location endCaveCoordinates;
  private final boolean gameOver = false;

  /**
   * Constructor for the game which takes in 4 parameters rows, columns, interconnectivity
   * and wrapping.
   */
  public Game(int rows, int columns, int interconnectivity, String wrapping, int perc, int n) {
    createGameDungeon(rows, columns, interconnectivity, wrapping, perc, n);
  }

  @Override
  public Node[][] createGameDungeon(int rows, int columns, int interconnectivity,
                                    String wrapping, int perc, int n) {
    dungeon2D = new DungeonImpl(rows, columns, interconnectivity, wrapping, perc, n);
    assignGameTreasure(perc);
    assignArrowsToGame(perc);

    //setStartAndEndCaveToTestIt(12, 1);
    startGame();
    System.out.println("Start Cave -> " + startCaveLocId + " End Cave -> " + endCaveLocId);
    assignMonsterToGame(dungeon2D.getNumberOfMonsters(), startCaveLocId, endCaveLocId);
    for (int i = 0; i < getRows(); i++) {
      for (int j = 0; j < columns; j++) {
        System.out.println(dungeon2D.get2dDungeon()[i][j].getMonster());
      }
    }

    return dungeon2D.get2dDungeon();
  }

  @Override
  public Node[][] restartGame(int rows, int columns, int interconnectivity,
                              String wrapping, int perc, int n) {
    dungeon2D = new DungeonImpl(rows, columns, interconnectivity, wrapping, perc, n);
    assignGameTreasure(perc);
    assignArrowsToGame(perc);

    //setStartAndEndCaveToTestIt(12, 1);
    startGame();
    assignMonsterToGame(dungeon2D.getNumberOfMonsters(), startCaveLocId, endCaveLocId);
    for (int i = 0; i < getRows(); i++) {
      for (int j = 0; j < columns; j++) {
        System.out.println(dungeon2D.get2dDungeon()[i][j].getMonster());
      }
    }

    return dungeon2D.get2dDungeon();
  }

  @Override
  public void setRows(String s) {
    int rows = Integer.parseInt(s);
  }

  @Override
  public void assignGameTreasure(int perc) {
    dungeon2D.assignTreasure(perc);
  }

  @Override
  public void assignMonsterToGame(int n, int startCaveLocId, int endCaveLocId) {
    dungeon2D.assignMonster(n, startCaveLocId, endCaveLocId);
  }

  @Override
  public Node[][] getGame2dDungeon() {
    return dungeon2D.get2dDungeon();
  }

  @Override
  public ArrayList<Moves> getReachableNodes(int locId) {
    return dungeon2D.getNodesReachableFromCurrentNode(locId);
  }

  @Override
  public void startGame() {
    assignStartAndEndCave();
    makePlayer();
  }

  @Override
  public String movePlayerEast() {
    String moveRes = "Player cannot move east as there is no path towards east."
            + " Please choose some other direction!";
    Location currLocOfPlayer = play.getPlayerCoordinates();
    ArrayList<Integer> adjacents = dungeon2D.get2dDungeon()
            [currLocOfPlayer.getX()][currLocOfPlayer.getY()].getAdjacents();
    int a = dungeon2D.get2dDungeon()[currLocOfPlayer.getX()][currLocOfPlayer.getY()].getLocId();
    //If the player already reached the end cave and move is called
    if (currLocOfPlayer == endCaveCoordinates) {
      moveRes = "Cannot move Player as he has already reached the destination!";
    }
    //if the dungeon is wrapping and player wants to move east

    if (a % dungeon2D.getRows() == 0) {
      if (dungeon2D.getWrappingStatus() && adjacents.contains(a - dungeon2D.getRows() + 1)) {
        int visLocId = a - dungeon2D.getRows() + 1;
        Location l = dungeon2D.getLocFromLocId(a - dungeon2D.getRows() + 1);
        dungeon2D.get2dDungeon()[l.getX()][l.getY()].setVisited(visLocId);
        //dungeon2D.get2dDungeon()[currLocOfPlayer.getX()][currLocOfPlayer.getY()].setVisited(a);
        play.setPlayerLocId(a - dungeon2D.getRows() + 1);
        play.setCoordinatesOfPlayer(dungeon2D.getLocFromLocId(a - dungeon2D.getRows() + 1));
        moveRes = "Player has moved east to Node " + (a - dungeon2D.getRows() + 1);

      }
    } else if (adjacents.contains(a + 1)) {
      int visLocId = a + 1;
      Location l1 = dungeon2D.getLocFromLocId(a+1);
      dungeon2D.get2dDungeon()[l1.getX()][l1.getY()].setVisited(visLocId);
      //dungeon2D.get2dDungeon()[currLocOfPlayer.getX()][currLocOfPlayer.getY()].setVisited(a + 1);
      play.setPlayerLocId(a + 1);
      play.setCoordinatesOfPlayer(dungeon2D.getLocFromLocId(a + 1));
      moveRes = "Player has moved east to Node " + (a + 1);

    }

    return moveRes;
  }

  @Override
  public String movePlayerWest() {
    String moveRes = "Player cannot move west as there is no path towards west."
            + " Please choose some other direction!";
    Location currLocOfPlayer = play.getPlayerCoordinates();
    ArrayList<Integer> adjacents = dungeon2D.get2dDungeon()
            [currLocOfPlayer.getX()][currLocOfPlayer.getY()].getAdjacents();
    int a = dungeon2D.get2dDungeon()[currLocOfPlayer.getX()][currLocOfPlayer.getY()].getLocId();
    //If the player already reached the end cave and move is called
    if (currLocOfPlayer == endCaveCoordinates) {
      moveRes = "Cannot move Player as he has already reached the destination!";
    }
    //if the dungeon is wrapping and player wants to move west

    if (a % dungeon2D.getRows() == 1) {
      if (dungeon2D.getWrappingStatus()
              && adjacents.contains(dungeon2D.getRows())) {
        int visLocId = dungeon2D.getRows();
        Location l = dungeon2D.getLocFromLocId(visLocId);
        dungeon2D.get2dDungeon()[l.getX()][l.getY()].setVisited(visLocId);
        //dungeon2D.get2dDungeon()[currLocOfPlayer.getX()][currLocOfPlayer.getY()].setVisited(a);
        play.setPlayerLocId(dungeon2D.getRows());
        play.setCoordinatesOfPlayer(dungeon2D.getLocFromLocId(dungeon2D.getRows()));
        moveRes = "Player has moved west to Node " + (dungeon2D.getRows());
      }
    } else if (adjacents.contains(a - 1)) {
      int visLocId = a - 1;
      Location l1 = dungeon2D.getLocFromLocId(visLocId);
      dungeon2D.get2dDungeon()[l1.getX()][l1.getY()].setVisited(visLocId);
      //dungeon2D.get2dDungeon()[currLocOfPlayer.getX()][currLocOfPlayer.getY()].setVisited(a - 1);
      play.setPlayerLocId(a - 1);
      play.setCoordinatesOfPlayer(dungeon2D.getLocFromLocId(a - 1));
      moveRes = "Player has moved west to Node " + (a - 1);
    }
    return moveRes;
  }

  @Override
  public String movePlayerNorth() {
    String moveRes = "Player cannot move north as there is no path towards north."
            + " Please choose some other direction!";
    Location currLocOfPlayer = play.getPlayerCoordinates();
    ArrayList<Integer> adjacents = dungeon2D.get2dDungeon()
            [currLocOfPlayer.getX()][currLocOfPlayer.getY()].getAdjacents();
    int a = dungeon2D.get2dDungeon()[currLocOfPlayer.getX()][currLocOfPlayer.getY()].getLocId();
    //If the player already reached the end cave and move is called
    if (currLocOfPlayer == endCaveCoordinates) {
      moveRes = "Cannot move Player as he has already reached the destination!";
    }
    //if the dungeon is wrapping and player wants to move north

    if (a % dungeon2D.getColumns() == 0) {
      if (dungeon2D.getWrappingStatus()
              && adjacents.contains(dungeon2D.getRows() * dungeon2D.getColumns())) {
        int visLocId = dungeon2D.getRows() * dungeon2D.getColumns();
        Location l = dungeon2D.getLocFromLocId(visLocId);
        dungeon2D.get2dDungeon()[l.getX()][l.getY()].setVisited(visLocId);
        play.setPlayerLocId(dungeon2D.getRows() * dungeon2D.getColumns());
        play.setCoordinatesOfPlayer(dungeon2D.getLocFromLocId(dungeon2D.getRows()
                * dungeon2D.getColumns()));

        moveRes = "Player has moved north to Node " + visLocId;
      }
    } else if (adjacents.contains(a - dungeon2D.getColumns())) {
      int visLocId = a - dungeon2D.getColumns();
      Location l1 = dungeon2D.getLocFromLocId(visLocId);
      dungeon2D.get2dDungeon()[l1.getX()][l1.getY()].setVisited(visLocId);
      play.setPlayerLocId(a - dungeon2D.getColumns());
      play.setCoordinatesOfPlayer(dungeon2D.getLocFromLocId(a - dungeon2D.getColumns()));
      moveRes = "Player has moved north to Node " + visLocId;
    }
    return moveRes;
  }

  @Override
  public String movePlayerSouth() {
    String moveRes = "Player cannot move south as there is no path towards south."
            + " Please choose some other direction!";
    Location currLocOfPlayer = play.getPlayerCoordinates();
    ArrayList<Integer> adjacents = dungeon2D.get2dDungeon()
            [currLocOfPlayer.getX()][currLocOfPlayer.getY()].getAdjacents();
    int a = dungeon2D.get2dDungeon()[currLocOfPlayer.getX()][currLocOfPlayer.getY()].getLocId();
    //If the player already reached the end cave and move is called
    if (currLocOfPlayer == endCaveCoordinates) {
      moveRes = "Cannot move Player as he has already reached the destination!";
    }
    //if the dungeon is wrapping and player wants to move south

    if (a % (dungeon2D.getRows() * dungeon2D.getColumns()) == 0) {
      if (dungeon2D.getWrappingStatus()
              && adjacents.contains(a % dungeon2D.getColumns())) {
        int visLocId = a % dungeon2D.getColumns();
        Location l = dungeon2D.getLocFromLocId(visLocId);
        dungeon2D.get2dDungeon()[l.getX()][l.getY()].setVisited(visLocId);
        play.setPlayerLocId(a % dungeon2D.getColumns());
        play.setCoordinatesOfPlayer(dungeon2D.getLocFromLocId(a % dungeon2D.getColumns()));
        moveRes = "Player has moved south to Node " + visLocId;
      }
    } else if (adjacents.contains(a + dungeon2D.getColumns())) {
      int visLocId = (a + dungeon2D.getColumns());
      Location l1 = dungeon2D.getLocFromLocId(visLocId);
      dungeon2D.get2dDungeon()[l1.getX()][l1.getY()].setVisited(visLocId);
      play.setPlayerLocId(a + dungeon2D.getColumns());
      play.setCoordinatesOfPlayer(dungeon2D.getLocFromLocId(a + dungeon2D.getColumns()));
      moveRes = "Player has moved north to Node " + visLocId;

    }
    return moveRes;
  }

  @Override
  public void setStartAndEndCaveToTestIt(int s, int e) {
    this.startCaveLocId = s;
    this.endCaveLocId = e;
    this.startCaveCoordinates = dungeon2D.getLocFromLocId(startCaveLocId);
    this.endCaveCoordinates = dungeon2D.getLocFromLocId(endCaveLocId);
    makePlayer();
  }

  private void assignStartAndEndCave() {
    ArrayList<Integer> setOfNodes = this.dungeon2D.getCaves();
    //Randomly choosing a start cave
    int ran = RandomNumberGenerator.genRan(setOfNodes.size(), 0);
    startCaveLocId = setOfNodes.get(ran);
    startCaveCoordinates = dungeon2D.getLocFromLocId(startCaveLocId);
    setOfNodes.remove(ran);
    ran = RandomNumberGenerator.genRan(setOfNodes.size(), 0);
    endCaveLocId = setOfNodes.get(ran);
    endCaveCoordinates = dungeon2D.getLocFromLocId(endCaveLocId);
    //System.out.println("Start Cave = " + startCaveLocId + "End Cave = " + endCaveLocId);
  }

  private void makePlayer() {
    play = new PlayerImpl(startCaveCoordinates, startCaveLocId);
  }

  @Override
  public int getStartCaveLocId() {
    return startCaveLocId;
  }

  @Override
  public int getEndCaveLocId() {
    return endCaveLocId;
  }

  @Override
  public Player getPlayer() {
    return play;
  }

  @Override
  public boolean getMonsterStatus(int locId) {
    return dungeon2D.isMonsterPresent(locId);
  }

  @Override
  public int getSmell() {
    Location currLocOfPlayer = play.getPlayerCoordinates();
    ArrayList<Integer> adjacents = dungeon2D.get2dDungeon()
            [currLocOfPlayer.getX()][currLocOfPlayer.getY()].getAdjacents();
    int sw = 0;
    //int l = dungeon2D.get2dDungeon()[currLocOfPlayer.getX()][currLocOfPlayer.getY()].getLocId();
    for (Integer i : adjacents) {
      if (getMonsterStatus(i)) {
        sw = 1;
      }
      if (sw != 1) {
        for (int j = 0; j < adjacents.size(); j++) {
          int lo = adjacents.get(j);
          Location loc = dungeon2D.getLocFromLocId(lo);
          ArrayList<Integer> adjAtDist2;
          adjAtDist2 = dungeon2D.get2dDungeon()[loc.getX()][loc.getY()].getAdjacents();
          for (Integer y : adjAtDist2) {
            if (getMonsterStatus(y)) {
              sw = 2;
            }
          }
        }
      }
    }
    return sw;
  }

  @Override
  public void assignArrowsToGame(int perc) {
    dungeon2D.assignArrows(perc);
  }

  @Override
  public String collectTreasure() {
    Location currLocOfPlayer = this.play.getPlayerCoordinates();
    if (dungeon2D.get2dDungeon()[currLocOfPlayer.getX()]
            [currLocOfPlayer.getY()].getTreasure().size() == 0
            || dungeon2D.get2dDungeon()[currLocOfPlayer.getX()]
            [currLocOfPlayer.getY()].getLocType() == LocationType.TUNNEL) {
      return "No treasure present at the location!";
    } else {
      ArrayList<TreasureI> trList = dungeon2D.get2dDungeon()[currLocOfPlayer.getX()]
              [currLocOfPlayer.getY()].getTreasure();
      for (TreasureI treasureI : trList) {
        play.addTreasure(treasureI);
      }
    }
    while (dungeon2D.get2dDungeon()[currLocOfPlayer.getX()]
            [currLocOfPlayer.getY()].getTreasure().size() != 0) {
      dungeon2D.get2dDungeon()[currLocOfPlayer.getX()][currLocOfPlayer.getY()].removeTreasure(0);
    }
    return "Successfully collected the treasure!";
  }


  @Override
  public String shootArrow(Directions d, int dist) {
    if (this.play.getArrowsEqipped() == 0) {
      return "Player does not have any arrows left!";
    } else {
      this.play.decrementArrow();
      ArrayList<Integer> pathOfArrow = new ArrayList<>();
      int arLocId = this.play.getPlayerLocId();
      Location arLoc = this.dungeon2D.getLocFromLocId(arLocId);
      LocationType arLocType = this.dungeon2D.get2dDungeon()[arLoc.getX()]
              [arLoc.getY()].getLocType();
      boolean canMoveHappen = false;
      int arrowDistSoFar = 0;

      ArrayList<Moves> possibleMoves = this.dungeon2D.get2dDungeon()[arLoc.getX()]
              [arLoc.getY()].getPossibleMoves();

      for (int i = 0; i < possibleMoves.size(); i++) {
        if (possibleMoves.get(i).getMovesForCurrentNode() == d) {
          canMoveHappen = true;
          pathOfArrow.add(arLocId);
          pathOfArrow.add(possibleMoves.get(i).getLocId());
          arLocId = possibleMoves.get(i).getLocId();
          arLoc = this.dungeon2D.getLocFromLocId(arLocId);
          arLocType = this.dungeon2D.get2dDungeon()[arLoc.getX()][arLoc.getY()].getLocType();
          if (arLocType == LocationType.CAVE) {
            arrowDistSoFar++;
          }
          break;
        }
      }

      if (!canMoveHappen) {
        return "Invalid move!";
      } else {
        while (arrowDistSoFar < dist) {
          possibleMoves = this.dungeon2D.get2dDungeon()[arLoc.getX()]
                  [arLoc.getY()].getPossibleMoves();
          canMoveHappen = false;

          if (arLocType == LocationType.TUNNEL) {
            canMoveHappen = true;
            ArrayList<Integer> adjacentsOfThisNode = dungeon2D.get2dDungeon()[arLoc.getX()]
                    [arLoc.getY()].getAdjacents();
            int recentlyVisitedNode = pathOfArrow.get(pathOfArrow.size() - 1);
            adjacentsOfThisNode.remove(adjacentsOfThisNode.indexOf(recentlyVisitedNode));
            arLocId = adjacentsOfThisNode.remove(0);
            pathOfArrow.add(arLocId);
            arLoc = this.dungeon2D.getLocFromLocId(arLocId);
            arLocType = this.dungeon2D.get2dDungeon()[arLoc.getX()][arLoc.getY()].getLocType();
          } else {
            for (int i = 0; i < possibleMoves.size(); i++) {
              if (possibleMoves.get(i).getMovesForCurrentNode() == d) {
                canMoveHappen = true;
                pathOfArrow.add(possibleMoves.get(i).getLocId());
                arLocId = possibleMoves.get(i).getLocId();
                arLoc = this.dungeon2D.getLocFromLocId(arLocId);
                arLocType = this.dungeon2D.get2dDungeon()[arLoc.getX()][arLoc.getY()].getLocType();
                break;
              }
            }
            if (arLocType == LocationType.CAVE) {
              arrowDistSoFar++;
            }
            if (arrowDistSoFar == dist) {
              break;
            }
          }
          if (!canMoveHappen) {
            break;
          }
        }
      }
      int lastLocationIdOfArrow = pathOfArrow.get(pathOfArrow.size() - 1);
      Location lastOfLocationOfArrow = dungeon2D.getLocFromLocId(lastLocationIdOfArrow);
      if (arrowDistSoFar == dist && !dungeon2D.get2dDungeon()[lastOfLocationOfArrow.getX()]
              [lastOfLocationOfArrow.getY()].getMonster().isEmpty()
      ) {
        dungeon2D.get2dDungeon()[lastOfLocationOfArrow.getX()]
                [lastOfLocationOfArrow.getY()].getMonster().get(0).decrementHealth();
        int monHealth = dungeon2D.get2dDungeon()[lastOfLocationOfArrow.getX()]
                [lastOfLocationOfArrow.getY()].getMonster().get(0).getHealth();
        if (monHealth == 0) {
          return "Monster killed";
        } else {
          return "Monster injured";
        }
      } else {
        return "Player has missed the shot";
      }
    }
  }

  @Override
  public ArrayList<Treasure> getGameTreasure() {
    return dungeon2D.getTreasure();
  }

  @Override
  public Location getPlayLocFromLocId(int locId) {
    return this.dungeon2D.getLocFromLocId(locId);
  }

  @Override
  public void pickUpArrow() {
    play.incrementArrow();
    Location l = getPlayLocFromLocId(play.getPlayerLocId());
    this.dungeon2D.get2dDungeon()[l.getX()][l.getY()].updateArrow(false);
  }

  @Override
  public boolean isGameOver() {
    return !play.isPlayerAlive(this) || getPlayer().getPlayerLocId() == getEndCaveLocId();
  }

  @Override
  public int getRows() {
    return dungeon2D.getRows();
  }

  @Override
  public int getColumns() {
    return dungeon2D.getColumns();
  }

  @Override
  public void printDungeonGame(int rows, int columns, GameI g) {
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        System.out.print(g.getGame2dDungeon()[i][j].toString() + "\n");
      }
      System.out.println();
    }
  }

  @Override
  public void setCols(String text) {
    int columns = Integer.parseInt(text);
  }

  @Override
  public void setInt(String s) {
    int interconnectivity = Integer.parseInt(s);
  }

  @Override
  public void setWrapping(String s) {
  }

  @Override
  public void setPerc(String s) {
    int perc = Integer.parseInt(s);
  }

  @Override
  public void setNumOfMon(String s) {
    int numOfMons = Integer.parseInt(s);
  }
}

