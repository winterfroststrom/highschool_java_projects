/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package refactored_game.Objs.Walls;

import refactored_game.Main;
import refactored_game.Objs.Craft;
import refactored_game.support.Point;

/**
 *
 * @author Sparky
 */
public class BorderWall extends Wall {

    public BorderWall(Point p, int w, int h, Main base) {
        super(p, w, h, base, 0);
        collideDamage = 0;
    }

    @Override
    public int hit(Craft c) {
        return collideDamage;
    }

    @Override
    public void hit(int d) {
    }

    @Override
    public boolean isDead() {
        return false;
    }
}