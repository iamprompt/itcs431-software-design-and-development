package th.ac.mahidol.ict.heroesbackend.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import th.ac.mahidol.ict.heroesbackend.model.Hero;
import th.ac.mahidol.ict.heroesbackend.model.Human;
import th.ac.mahidol.ict.heroesbackend.model.Superhuman;
import th.ac.mahidol.ict.heroesbackend.model.Villain;
import th.ac.mahidol.ict.heroesbackend.model.Weapon;
import th.ac.mahidol.ict.heroesbackend.repository.SuperhumanRepository;

@Controller
public class SuperhumanController {
  @Autowired
  private SuperhumanRepository superhumanRepository;

  @CrossOrigin
  @GetMapping("/")
  public @ResponseBody String welcome() {
    // show the welcome page at the root
    return "Hello, welcome to the Heroes application";
  }

  @CrossOrigin
  @GetMapping("/heroes")
  public @ResponseBody List<Superhuman> getAllHeroes() {
    // This returns list of JSON objects of all the heroes
    System.out.println(superhumanRepository.findAll());
    return superhumanRepository.findAll();
  }

  @CrossOrigin
  @PostMapping("/heroes")
  public @ResponseBody String addHero(@RequestBody Map<String, Object> body) {
    System.out.println(body);
    if (body.get("type").toString().equals("hero")) {
      Hero h = createNewHeroWithId(Integer.parseInt(body.get("id").toString()), body);
      superhumanRepository.save(h);
      return "Saved: " + h;
    } else if (body.get("type").toString().equals("villain")) {
      Villain v = createNewVillainWithId(Integer.parseInt(body.get("id").toString()), body);
      superhumanRepository.save(v);
      return "Saved: " + v;
    }
    return "Error: wrong superhuman type";
  }

  // Get a hero by id
  @CrossOrigin
  @GetMapping("/heroes/{id}")
  public @ResponseBody Superhuman getHero(@PathVariable("id") int id) {
    // This returns a JSON object of the hero with the id
    return superhumanRepository.findById(id).get();
  }

  // Update a hero by id
  @CrossOrigin
  @PutMapping("/heroes/{id}")
  public @ResponseBody String updateHero(@PathVariable("id") int id, @RequestBody Map<String, Object> body) {
    // This returns a JSON object of the hero with the id
    Superhuman h = superhumanRepository.findById(id).get();
    if (h.getType().equals("hero")) {
      h = createNewHeroWithId(id, body);
      superhumanRepository.save(h);
      return "Updated: " + h;
    } else if (h.getType().equals("villain")) {
      h = createNewVillainWithId(id, body);
      superhumanRepository.save(h);
      return "Updated: " + h;
    }
    return "Error: wrong superhuman type";
  }

  @CrossOrigin
  @DeleteMapping("/heroes/{id}")
  public @ResponseBody String deleteHero(@PathVariable("id") int id) {
    // This returns a JSON object of the hero with the id
    Optional<Superhuman> h = superhumanRepository.findById(id);

    if (h.isEmpty()) {
      return "Error: no such hero";
    }

    Superhuman hero = h.get();
    superhumanRepository.delete(hero);
    return "Deleted: hero " + id;
  }

  @CrossOrigin
  @DeleteMapping("/heroes")
  public @ResponseBody String deleteHeroAll() {
    superhumanRepository.deleteAll();
    return "Deleted: all heroes";
  }

  private Hero createNewHeroWithId(int id, Map<String, Object> body) {
    // create a new hero with the given info
    Hero h = new Hero();
    h.setId(id);
    return setHeroInfo(h, body);
  }

  private Villain createNewVillainWithId(int id, Map<String, Object> body) {
    // create a new villain with the given info
    Villain v = new Villain();
    v.setId(id);
    return setVillainInfo(v, body);
  }

  private Hero setHeroInfo(Hero h, Map<String, Object> body) {
    h.setName(body.get("name").toString());
    h.setType(body.get("type").toString());
    h.setRealname(body.get("realname").toString());
    h.setSuperpower(body.get("superpower").toString());
    h.setImageURL(body.get("imageURL").toString());
    if (body.get("weapons") != null) {
      h.setWeapons(createWeaponList((ArrayList<Map<String, Object>>) body.get("weapons"), h));
    }
    if (body.get("humanFriends") != null) {
      h.setHumanFriends(createFriendList((ArrayList<Map<String, Object>>) body.get("humanFriends"), h));
    }
    return h;
  }

  private Villain setVillainInfo(Villain v, Map<String, Object> body) {
    v.setName(body.get("name").toString());
    v.setType(body.get("type").toString());
    v.setOrigin(body.get("origin").toString());
    v.setSuperpower(body.get("superpower").toString());
    v.setImageURL(body.get("imageURL").toString());
    if (body.get("weapons") != null) {
      v.setWeapons(createWeaponList((ArrayList<Map<String, Object>>) body.get("weapons"), v));
    }
    if (body.get("humanFriends") != null) {
      v.setHumanFriends(createFriendList((ArrayList<Map<String, Object>>) body.get("humanFriends"), v));
    }
    return v;
  }

  private List<Weapon> createWeaponList(ArrayList<Map<String, Object>> weapons, Superhuman h) {
    List<Weapon> weaponList = new ArrayList<>();
    for (Map<String, Object> o : weapons) {
      System.out.println(o);
      Weapon w = new Weapon(o.get("name").toString(), o.get("description").toString(), h);
      weaponList.add(w);
    }
    return weaponList;
  }

  private List<Human> createFriendList(ArrayList<Map<String, Object>> friends, Superhuman h) {
    List<Human> friendList = new ArrayList<>();
    for (Map<String, Object> o : friends) {
      System.out.println(o);
      Human friend = new Human(o.get("name").toString());
      friend.addFriend(h);
      friendList.add(friend);
    }
    return friendList;
  }

}
