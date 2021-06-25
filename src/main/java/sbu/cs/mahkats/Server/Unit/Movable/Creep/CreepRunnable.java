package sbu.cs.mahkats.Server.Unit.Movable.Creep;

import sbu.cs.mahkats.Server.App.GamePlay;
import sbu.cs.mahkats.Server.Unit.Unit;

import java.util.ArrayList;

public class CreepRunnable implements Runnable {
    private ArrayList<Creep> creeps;
    public CreepRunnable(ArrayList<Creep> creeps){
        this.creeps = creeps;
    }

    private void handler() {
        for(Creep c : creeps){
            if (c.getStatusAttacker()) {
                //TODO: message to client for attack
                c.getDefender().takeDamage(c.getDamage());
            }
            else {
                c.move(c.getLane());
            }
        }
        
    }

    private void checkCreepsAlive(){
        creeps.removeIf(Unit::getStatusDie);
    }

    @Override
    public void run(){
        int lastTurn = GamePlay.getTurn();
        while(true) {
            if (GamePlay.getTurn() > lastTurn) {
                checkCreepsAlive();
                handler();
                lastTurn = GamePlay.getTurn();
            }
        }
    
    }
}

