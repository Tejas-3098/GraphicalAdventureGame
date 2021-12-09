package dungeon;

/**
 * Monster class represents the monster along with monster constructor and contains methods to set
 * and get the health of the monster.
 */
public class Monster {
  private int health;

  public Monster(int health) {
    this.health = 2;
  }

  public void setHealth(int h) {
    this.health = h;
  }

  public int getHealth() {
    return this.health;
  }

  public void decrementHealth() {
    this.health -= 1;
  }
}
