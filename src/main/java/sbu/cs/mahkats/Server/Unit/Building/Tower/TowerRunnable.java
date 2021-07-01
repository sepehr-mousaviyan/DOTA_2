package sbu.cs.mahkats.Server.Unit.Building.Tower;

import sbu.cs.mahkats.Server.App.GamePlay;
import sbu.cs.mahkats.Server.Unit.Unit;

import java.util.ArrayList;

public class TowerRunnable implements Runnable {
    private final ArrayList<Tower> towers;

    public TowerRunnable(ArrayList<Tower> towers) {
        this.towers = towers;
    }

    private void checkCreepsAlive() {
        towers.removeIf(Unit::getStatusDie);
    }

    @Override
    public void run() {
        int lastTurn = GamePlay.getTurn();
        while (true) {
            if (GamePlay.getTurn() > lastTurn) {
                checkCreepsAlive();
                for (Tower tower : towers) {
                    if (tower.getStatusAttacker()) {
                        tower.getDefender().takeDamage(tower.getDamage());
                    }
                }
            }
        }
    }
}
