package th.ac.mahidol.ict.heroes;

import th.ac.mahidol.ict.human.Human;

import java.util.ArrayList;

public abstract class Superhuman {
  private String name;
  private String superpower;
  protected ArrayList<String> weapons;
  private ArrayList<Human> friends;

  public Superhuman(String name, String superpower) {
    this.name = name;
    this.superpower = superpower;
    this.weapons = new ArrayList<>();
    this.friends = new ArrayList<>();
  }

  // this is method overloading
  public String getWeapon() {
    // return the first weapon
    return weapons.get(0);
  }

  // this is method overloading
  public String getWeapon(String name) {
    for (String w : weapons) {
      if (w.equals(name)) {
        return w;
      }
    }
    return "";
  }

  public void addWeapon(String weapon) {
    this.weapons.add(weapon);
  }

  public void addFriend(Human friend) {
    friends.add(friend);
  }

  public void setWeapons(ArrayList<String> weapons) {
    this.weapons = weapons;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSuperpower() {
    return superpower;
  }

  public void setSuperpower(String superpower) {
    this.superpower = superpower;
  }

  public ArrayList<String> getWeapons() {
    return weapons;
  }

  public ArrayList<Human> getFriends() {
    return friends;
  }

  @Override
  public String toString() {
    return "Superhuman{" +
        "name='" + name + '\'' +
        ", superpower='" + superpower + '\'' +
        ", weapons=" + weapons +
        '}';
  }
}
