/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package refactored_game.Objs;

import refactored_game.Main;
import refactored_game.support.Point;
import refactored_game.Objs.Craft;
import refactored_game.interfaces.Tangible;

/**
 *
 * @author Sparky
 */
public class Explosion implements Tangible{
    public Point p;
    public int counter = 10;
    public int w;
    public int h;
    public Main base;

    public Explosion(Point p, int w, int h, Main base) {
        this.p = new Point(p.x - 5, p.y - 5);
        this.base = base;
        this.w = w + 10;
        this.h = h + 10;
    }

    public int hit(Craft c) {
        counter = 0;
        return 0;
    }

    @Override
    public boolean isDead() {
        counter--;
        return counter <= 0;
    }

    @Override
    public boolean notInBounds() {
        return false;
    }
}
