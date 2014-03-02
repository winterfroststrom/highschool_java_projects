/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package refactored_game.Levels;

import java.util.ArrayList;
import refactored_game.Main;

/**
 *
 * @author Sparky
 */
public class Level {

    public ArrayList<Spawn> spawn;
    public ArrayList<Enviroment> enviroment;
    public int sCounter = 0;
    public int eCounter = 0;
    public Main base;
    public boolean bossOn = false;

    public Level(Main base) {
        this.spawn = new ArrayList<Spawn>();
        this.enviroment = new ArrayList<Enviroment>();
        this.base = base;
    }

    public void next() {
        if (bossOn) {
            return;
        }
        nextE();
        nextS();
    }

    public void nextE() {
        if (eCounter < enviroment.size()) {
            Enviroment e = enviroment.get(eCounter);
            e.counter--;
            while (eCounter < enviroment.size() && enviroment.get(eCounter).counter <= 0) {
                base.objects.add(enviroment.get(eCounter).event);
                eCounter++;
            }
        }
    }

    public void nextS() {
        if (sCounter < spawn.size()) {
            Spawn s = spawn.get(sCounter);
            s.counter--;
            while (sCounter < spawn.size() && spawn.get(sCounter).counter <= 0) {
                if (spawn.get(sCounter).event.isBoss()) {
                    bossOn = true;

                }
                base.enemies.add(spawn.get(sCounter).event);
                sCounter++;
            }
        }
    }

    public boolean isEnd() {
        return sCounter >= spawn.size() && eCounter >= enviroment.size();
    }
}
