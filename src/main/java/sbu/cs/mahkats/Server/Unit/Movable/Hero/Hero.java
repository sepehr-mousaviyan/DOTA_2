package sbu.cs.mahkats.Server.Unit.Movable.Hero;

import sbu.cs.mahkats.Server.Unit.Movable.Movable;

public class Hero extends Movable {
    String ability1;
    String ability2;
    String ability3;
    String ability4;

    


    public Hero(String teamName , int code) {
        super(teamName ,"Hero" , code);
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
