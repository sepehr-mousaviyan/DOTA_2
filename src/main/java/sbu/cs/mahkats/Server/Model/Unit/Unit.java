package sbu.cs.mahkats.Server.Model.Unit;

import org.javatuples.Pair;

public interface Unit {
    int hp = 0;
    int hp_regeneration = 0;
    int damage = 0;
    int armor = 0;
    int range = 0;
    int Location_x = 0;
    int Location_y = 0;

    //TODO regenerate hp
    public void Hp_regenerate();
//    {
//        hp  = hp  + hp_regeneration;
//    }
    //TODO damage giving

    //TODO damage taking
    public void takeDamage(Unit Opponent);
//    {
//        int damageDealt = Opponent.getDamage();
//        hp  = hp  - (damageDealt - armor);
//    }

    
}
