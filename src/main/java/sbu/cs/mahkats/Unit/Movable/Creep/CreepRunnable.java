package sbu.cs.mahkats.Unit.Movable.Creep;

import sbu.cs.mahkats.Server.App.GamePlay;

import java.util.ArrayList;

public class CreepRunnable implements Runnable {
    private ArrayList<Creep> creeps;
    public CreepRunnable(ArrayList<Creep> creeps){
        this.creeps = creeps;
    }

    private void handler() {
       /* if (opponent != null) {
            opponent().damage(creep.getAttack());
            creep.attack();
        }
        else {
            creep.move();
        }*/
    }

    @Override
    public void run(){
        int lastTurn = GamePlay.getTurn();
        while(true) {
            if (GamePlay.getTurn() - lastTurn == 1) {
                handler();
                lastTurn = GamePlay.getTurn();
            }
        }
    }
}

