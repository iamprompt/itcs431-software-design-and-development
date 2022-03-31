package th.ac.mahidol.ict.heroes;

public class Hero extends Superhuman {
    private String realname;

    public Hero(String name, String superpower, String realname) {
        super(name, superpower);
        this.realname = realname;
    }

    @Override
    public String getWeapon() {
        // return the last weapon
        return this.weapons.get(weapons.size() - 1);
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    @Override
    public String toString() {
        return "Hero{ " + super.toString() +
                ", realname='" + realname + '\'' +
                '}';
    }
}
