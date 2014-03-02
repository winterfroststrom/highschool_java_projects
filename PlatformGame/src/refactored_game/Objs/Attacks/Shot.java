/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package refactored_game.Objs.Attacks;

import java.awt.Shape;
import refactored_game.Objs.Craft;
import refactored_game.support.Line;
import refactored_game.Main;
import refactored_game.support.Point;
import refactored_game.Objs.SCraft;
import refactored_game.interfaces.Collidable;
import refactored_game.interfaces.Tangible;

/**
 *
 * @author Sparky
 */
public abstract class Shot implements Collidable, Tangible {

    public boolean hit = false;
    public Point p;
    public int speed;
    public Main base;
    public double scaleud;
    public double scalelr;
    public int dir;
    public int damage;

    public Shot(SCraft s, int speed, int damage, double scalelr, double scaleud) {
        this.p = s.getPoint();
        this.speed = speed;
        this.base = s.base;
        this.dir = s.dir;
        this.damage = damage;
        this.scalelr = scalelr;
        this.scaleud = scaleud;
    }

    public int hit(Craft c) {
        hit = true;
        return damage;
    }

    @Override
    public boolean isDead() {
        return hit;
    }

    @Override
    public boolean notInBounds() {
        if (this.construct().intersects(0, -10, 500, 510)) {
            return false;
        }
        return true;
    }

    @Override
    public void move() {
        p.x += (scalelr * speed * dir);
        p.y += (scaleud * speed * dir);
    }

    public Point falseMove() {
        return new Point(p.x + (scalelr * speed), p.y + (scaleud * speed * dir));
    }

    @Override
    public Line getPath() {
        return new Line(p.getPoint(), falseMove());
    }

    @Override
    public abstract Shape construct();

    public abstract void modifyPoint(SCraft s);
}