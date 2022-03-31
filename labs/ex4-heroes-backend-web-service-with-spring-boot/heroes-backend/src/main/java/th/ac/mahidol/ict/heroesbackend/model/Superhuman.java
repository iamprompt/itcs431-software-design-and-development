package th.ac.mahidol.ict.heroesbackend.model;

import javax.persistence.*;

import java.util.List;

@Entity
public class Superhuman {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  private String type;
  private String name;
  private String superpower;
  private String imageURL;

  @ManyToMany(mappedBy = "superhumanFriends", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private List<Human> humanFriends;

  @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
  private List<Weapon> weapons;

  public Superhuman() {
    super();
  }

  public Superhuman(int id, String name, String type, String superpower,
      String imageURL, List<Human> humanFriends) {
    this.id = id;
    this.name = name;
    this.type = type;
    this.superpower = superpower;
    this.imageURL = imageURL;
    this.humanFriends = humanFriends;
  }

  public List<Human> getHumanFriends() {
    return humanFriends;
  }

  public void setHumanFriends(List<Human> humanFriends) {
    this.humanFriends = humanFriends;
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

  public List<Weapon> getWeapons() {
    return weapons;
  }

  public void setWeapons(List<Weapon> weapons) {
    this.weapons = weapons;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getImageURL() {
    return imageURL;
  }

  public void setImageURL(String image) {
    this.imageURL = image;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return "Superhuman{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", superpower='" + superpower + '\'' +
        ", image='" + imageURL + '\'' +
        '}';
  }
}
