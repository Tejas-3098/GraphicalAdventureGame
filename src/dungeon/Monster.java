package dungeon;

/**
 * Monster class represents the monster along with monster constructor and contains methods to set
 * and get the health of the monster.
 */
public class Monster {
  private int health;

  public Monster(int health) {
    this.health = health;
  }

  public int getHealth() {
    return this.health;
  }

  public void decrementHealth() {
    this.health -= 1;
  }
}
