/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package refactored_game;

import refactored_game.Objs.Explosion;
import refactored_game.Objs.Token;
import java.util.ArrayList;
import java.util.Iterator;
import refactored_game.Objs.Craft;
import refactored_game.Objs.ECrafts.ECraft;
import refactored_game.interfaces.Tangible;

/**
 *
 * @author Sparky
 */
public class DeathManager {

    public void checkDie(Main base) {
        if (base.player.health <= 0) {
            base.notOver = false;
        }
        doCheckDie(base.pShots);
        doCheckDie(base.eShots);
        doCheckDie(base.tokens);
        doCheckDie(base.explosions);
        doCheckDieAndScore(base, base.objects);
        doCheckDieAndTokens(base, base.enemies);
    }

    private void doCheckDie(ArrayList<? extends Tangible> c) {
        Iterator<? extends Tangible> it = c.iterator();
        while (it.hasNext()) {
            Tangible temp = it.next();
            if (temp.isDead() || temp.notInBounds()) {
                it.remove();
            }
        }
    }

    private void doCheckDieAndScore(Main base, ArrayList<Craft> c) {
        Iterator<Craft> it = c.iterator();
        while (it.hasNext()) {
            Craft temp = it.next();
            if (temp.isDead() || temp.notInBounds()) {
                base.score += temp.healthMax / 10;
                it.remove();
            }
        }
    }

    private void doCheckDieAndTokens(Main base, ArrayList<ECraft> c) {
        Iterator<ECraft> it = c.iterator();
        while (it.hasNext()) {
            ECraft temp = it.next();
            if (temp.isDead() || temp.notInBounds()) {
                base.explosions.add(new Explosion(temp.p, temp.w, temp.h, base));
                base.score += temp.healthMax;
                it.remove();
                if (base.gen.nextInt(20) > 18) {
                    base.tokens.add(new Token(temp, randToken(base)));
                }
            }
        }
    }
    
    private int randToken(Main base){
        int sum = 1;
        for(int i = 0;i<base.player.upgrades.length-1;i++)
            if(base.player.upgrades[i] > 0)
                sum++;
        return base.gen.nextInt(sum);
    }
}
