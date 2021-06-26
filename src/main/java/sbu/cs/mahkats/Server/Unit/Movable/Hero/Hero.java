package sbu.cs.mahkats.Server.Unit.Movable.Hero;

import sbu.cs.mahkats.Server.Unit.Movable.Hero.Ability.Ability;
import sbu.cs.mahkats.Server.Unit.Movable.Movable;

public class Hero extends Movable {
<<<<<<< HEAD
    String ability1;
    String ability2;
    String ability3;
    String ability4;

    

=======
    String hero_name;
>>>>>>> implement ability class

    Ability ability1;
    Ability ability2;
    Ability ability3;

    public Hero(String teamName , int code, String hero_name) {
        super(teamName ,"Hero" , code);
        this.hero_name = hero_name;
        ability1 = new Ability(hero_name, 1);
        ability2 = new Ability(hero_name, 2);
        ability3 = new Ability(hero_name, 3);
    }

    public Ability getAbility1() {
        return ability1;
    }

    public void setAbility1(Ability ability1) {
        this.ability1 = ability1;
    }

    public Ability getAbility2() {
        return ability2;
    }

    public void setAbility2(Ability ability2) {
        this.ability2 = ability2;
    }

    public Ability getAbility3() {
        return ability3;
    }

    public void setAbility3(Ability ability3) {
        this.ability3 = ability3;
    }

    public String getAbility1() {
        return ability1;
    }

    public String getAbility2() {
        return ability2;
    }

    public String getAbility3() {
        return ability3;
    }

    public String getAbility4() {
        return ability4;
    }
    
}
