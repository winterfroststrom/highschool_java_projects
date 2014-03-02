/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package refactored_game.Objs;

import refactored_game.Main;
import refactored_game.support.Point;
import refactored_game.interfaces.Drawable;
import refactored_game.interfaces.Tangible;

/**
 *
 * @author Sparky
 */
public abstract class Craft extends RecObj implements Tangible, Drawable {
    public Main base;
    public int health = 15;
    public int healthMax = 15;
    public int collideDamage = 10;
    public int dir = 1;

    public Craft(Point p, int speed, int w, int h, Main base) {
        super(p, speed, w, h);
        this.base = base;
    }

    public Craft(Point p, int speed, int w, int h, Main base, int health) {
        super(p, speed, w, h);
        this.base = base;
        this.health = health;
        this.healthMax = health;
    }

    public int hit(Craft c) {
        this.health -= c.collideDamage;
        return collideDamage;
    }

    public void hit(int d) {
        this.health -= d;
    }

    public boolean notInBounds() {
        if (this.construct().intersects(-100, -100, 700, 650)) {
            return false;
        }
        return true;
    }

    public boolean isDead() {
        return health <= 0;
    }
}
