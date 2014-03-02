/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package refactored_game;

import refactored_game.Objs.Token;
import refactored_game.Objs.Attacks.Shot;
import refactored_game.Objs.Craft;
import refactored_game.Objs.SCraft;
import refactored_game.Objs.Walls.BorderWall;

/**
 *
 * @author Sparky
 */
public class MovementManager {

    private void setPositions(Main base) {
        base.player.move();
        if (base.player.notInBounds()) {
            base.player.moveUndo();
        }
        for (Shot s : base.pShots) {
            s.move();
        }
        for (Shot s : base.eShots) {
            s.move();
        }
        for (SCraft e : base.enemies) {
            e.move();
        }
        for (Craft o : base.objects) {
            o.move();
        }
    }

    private void checkCollideP(Main base) {
        for (SCraft e : base.enemies) {
            if (base.player.collide(e)) {
                e.hit(base.player.hit(e));
                base.player.moveUndo();
            }
        }
        for (Craft o : base.objects) {
            if (base.player.collide(o)) {
                o.hit(base.player.hit(o));
                base.player.moveUndo();
            }
        }
        for (BorderWall w : base.borders) {
            if (base.player.collide(w)) {
                if (w.collideDamage > 0) {
                    base.player.hit(w);
                }
                base.player.moveUndo();
            }
        }
        for (Token t : base.tokens) {
            if (base.player.collide(t)) {
                t.hit(base.player);
                base.player.upgrade(t.num);
            }
        }
    }

    private void checkCollideS(Main base) {
        for (Shot s : base.eShots) {
            if (base.player.collide(s)) {
                base.player.hit(s.hit(base.player));
                base.player.moveUndo();
            }
        }
        for (Shot s : base.pShots) {
            for (SCraft e : base.enemies) {
                if (e.collide(s)) {
                    e.hit(s.hit(e));
                }
            }
            for (Craft o : base.objects) {
                if (o.collide(s)) {
                    o.hit(s.hit(o));
                }
            }
        }
    }
    
    public void doMovement(Main base) {
        setPositions(base);
        checkCollideP(base);
        checkCollideS(base);
    }
}
