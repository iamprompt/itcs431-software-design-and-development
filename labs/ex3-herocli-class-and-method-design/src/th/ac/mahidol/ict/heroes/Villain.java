package th.ac.mahidol.ict.heroes;

import java.util.Random;

public class Villain extends Superhuman {
  private String origin;

  public Villain(String name, String superpower, String origin) {
    super(name, superpower);
    this.origin = origin;
  }

  @Override
  public String getWeapon() {
    // return random weapon
    Random rand = new Random();
    int randNo = rand.nextInt(this.weapons.size());
    return this.weapons.get(randNo);
  }

  public String getOrigin() {
    return origin;
  }

  public void setOrigin(String origin) {
    this.origin = origin;
  }

  @Override
  public String toString() {
    return "Villain{ " + super.toString() +
        ", origin='" + origin + '\'' +
        '}';
  }
}
