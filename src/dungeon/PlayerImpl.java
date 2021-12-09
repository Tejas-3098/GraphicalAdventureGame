package dungeon;

import java.util.ArrayList;
import java.util.Objects;

/**
 * PlayerImpl class represents the player and contains methods like getPlayerCoordinates,
 * getTreasurePicked, addTreasure, setCoordinatesOfPlayer and the important toString method
 * which provides description of the player.
 */
public class PlayerImpl implements Player {

  private ArrayList<TreasureI> treasurePicked;
  private Location coordinatesOfPlayer;
  private int playerLocId;
  private int smellIntensity = 0;
  private int arrowsEquipped;

  /**
   * Constructor for the playerImpl class which takes in co-ordinates and location ID of the player.
   */
  public PlayerImpl(Location playerCoordinates, int playerLocId) {
    this.coordinatesOfPlayer = playerCoordinates;
    this.playerLocId = playerLocId;
    this.treasurePicked = new ArrayList<>();
    this.arrowsEquipped = 3;
  }

  @Override
  public ArrayList<TreasureI> getTreasurePicked() {
    return this.treasurePicked;
  }

  @Override
  public Location getPlayerCoordinates() {
    return this.coordinatesOfPlayer;
  }

  @Override
  public int getPlayerLocId() {
    return this.playerLocId;
  }

  @Override
  public void addTreasure(TreasureI treasure) {
    treasurePicked.add(treasure);
  }

  @Override
  public void setCoordinatesOfPlayer(Location coordinatesOfPlayer) {
    this.coordinatesOfPlayer = coordinatesOfPlayer;
  }

  @Override
  public int getArrowsEqipped() {
    return this.arrowsEquipped;
  }

  @Override
  public String toString() {
    StringBuilder pl = new StringBuilder();
    System.out.println();
    //pl.append("Location ID = ").append(this.playerLocId);
    pl.append(" Treasure Collected -> ");
    pl.append(getTreasurePickedCount()).append(System.lineSeparator());
    pl.append("Arrows -> ");
    pl.append(getArrowsEqipped());
    return pl.toString();
  }

  private String getTreasurePickedCount() {
    String treasureCount = "";
    //ArrayList<Treasure> tre = new ArrayList<>();
    for (Treasure t : Treasure.values()) {
      int tc = 0;
      for (int i = 0; i < treasurePicked.size(); i++) {
        if (Objects.equals(t.toString(), treasurePicked.get(i).getTypeOfTreasure().toString())) {
          tc++;
        }
      }
      treasureCount = treasureCount + t.toString() + ":" + tc + " ";
    }
    return treasureCount;
  }

  @Override
  public void incrementArrow() {
    this.arrowsEquipped += 1;
  }

  @Override
  public void decrementArrow() {
    this.arrowsEquipped -= 1;
  }
  @Override
  public boolean isPlayerAlive(Game g) {
    boolean alive = true;
    Location plaLoc = getPlayerCoordinates();
    if (g.getGame2dDungeon()[plaLoc.getX()][plaLoc.getY()].getMonster().get(0) == null) {
      alive = true;
    } else {
      if (g.getGame2dDungeon()[plaLoc.getX()][plaLoc.getY()].getMonster().get(0) != null) {
        int h = g.getGame2dDungeon()[plaLoc.getX()][plaLoc.getY()].getMonster().get(0).getHealth();

        if (h == 2) {
          alive = false;
        }
        if (h == 1) {
          int ran = RandomNumberGenerator.genRan(1, 0);
          if (ran == 0) {
            alive = false;
          } else {
            alive = true;
          }
        }
      }
    }
    return alive;
  }

  @Override
  public void setPlayerLocId(int nextLocation) {
    this.playerLocId = nextLocation;
  }
}
