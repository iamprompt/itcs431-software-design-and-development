package th.ac.mahidol.ict.human;

import th.ac.mahidol.ict.heroes.Superhuman;

import java.util.ArrayList;

public class Human {
  private String name;
  private ArrayList<Superhuman> friends;

  public Human(String name) {
    this.name = name;
    this.friends = new ArrayList<>();
  }

  public void addFriend(Superhuman friend) {
    this.friends.add(friend);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ArrayList<Superhuman> getFriends() {
    return friends;
  }

  @Override
  public String toString() {
    return "Human{" +
        "name='" + name + '\'' +
        '}';
  }
}
