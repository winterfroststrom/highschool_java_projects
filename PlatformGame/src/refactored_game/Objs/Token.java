/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package refactored_game.Objs;

import refactored_game.support.Point;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import refactored_game.support.Line;
import refactored_game.Main;
import refactored_game.Objs.Craft;
import refactored_game.Objs.SCraft;
import refactored_game.interfaces.Collidable;
import refactored_game.interfaces.Tangible;

/**
 *
 * @author Sparky
 */
public class Token implements Tangible, Collidable {
    public Point p;
    public int counter = 200;
    public int w = 13;
    public int h = 13;
    public Main base;
    public int num;

    public Token(SCraft s, int num) {
        this.p = s.getPoint();
        this.base = s.base;
        if (num > -1 && num < 5) {
            this.num = num;
        }
    }

    public int hit(Craft c) {
        counter = 0;
        return 0;
    }

    @Override
    public boolean isDead() {
        return counter <= 0;
    }

    @Override
    public boolean notInBounds() {
        if (this.construct().intersects(0, 0, 500, 500)) {
            return false;
        }
        return true;
    }

    @Override
    public void move() {
        counter--;
    }

    @Override
    public Line getPath() {
        Point p1 = p.getPoint();
        Point p2 = p.getPoint();
        p1.x += this.w / 2;
        p2.x -= this.w / 2;
        p1.y += this.h / 2;
        p2.y -= this.h / 2;
        return new Line(p1, p2);
    }

    @Override
    public Shape construct() {
        return new Rectangle2D.Double(p.x, p.y, w, h);
    }
}
