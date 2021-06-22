package sbu.cs.mahkats.Server.App;


import sbu.cs.mahkats.Unit.Movable.Creep.Creep;

public class GamePlay {


    final Double REFRESH_RATE = 1D;
    final Double CREEP_GENERATE_TIME = 1D;
    unitList GreenUnits;
    unitList RedUnits;

    public GamePlay() {
        GreenUnits = new unitList("GREEN");
        RedUnits = new unitList("RED");
    }

    public void play() {

        long startTime = System.currentTimeMillis();
        long nowTime = System.currentTimeMillis();
        while(true) {
            if (System.currentTimeMillis() - startTime % CREEP_GENERATE_TIME == 0) {
                int randomGreenBarrack  = (int) (Math.random() * GreenUnits.towers.size()) + 1;
                int randomRedBarrack  = (int) (Math.random() * RedUnits.towers.size()) + 1;
                //from tower number ?
                GreenUnits.add(new Creep("GREEN"));
                RedUnits.add(new Creep("RED"));

            }
        }

    }
}
