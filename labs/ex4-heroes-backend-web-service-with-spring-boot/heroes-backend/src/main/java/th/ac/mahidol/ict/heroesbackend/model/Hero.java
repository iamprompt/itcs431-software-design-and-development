package th.ac.mahidol.ict.heroesbackend.model;

import java.util.List;

import javax.persistence.Entity;

@Entity
public class Hero extends Superhuman {
  private String realname;

  public Hero() {
    super();
  }

  public Hero(int id, String name, String type, String superpower,
      String realname, String imageURL, List<Human> humanFriends) {
    super(id, name, type, superpower, imageURL, humanFriends);
    this.realname = realname;
  }

  public String getRealname() {
    return realname;
  }

  public void setRealname(String realname) {
    this.realname = realname;
  }

  @Override
  public String toString() {
    return "Hero{" + super.toString() + ", realname='" + realname + '\'' +
        '}';
  }
}
