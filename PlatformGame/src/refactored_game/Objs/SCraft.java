/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package refactored_game.Objs;

import refactored_game.Objs.Attacks.RecShot;
import refactored_game.Objs.Attacks.Bullet;
import refactored_game.Objs.Attacks.BasicShot;
import java.util.ArrayList;
import refactored_game.Main;
import refactored_game.support.Point;
import refactored_game.interfaces.Shooter;

/**
 *
 * @author Sparky
 */
public abstract class SCraft extends Craft implements Shooter {
    public int bulletIndex = 0;
    public ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    public ArrayList<RecShot> addShots = base.eShots;
    public boolean shooting = false;

    public SCraft(Point p, int speed, int w, int h, Main base, int health) {
        super(p, speed, w, h, base);
        this.health = health;
        healthMax = health;
        bullets.add(new Bullet(new BasicShot(this, 10, 7, 7, 5)));
    }

    public SCraft(Point p, int speed, int w, int h, Main base) {
        this(p, speed, w, h, base, 4);
    }

    @Override
    public boolean canShoot() {
        return shooting && getBullet().isReady();
    }

    public Bullet getBullet() {
        return bullets.get(bulletIndex);
    }

    public boolean isBulletIndex(int index) {
        return bullets.size() > index;
    }

    public boolean isNotBulletIndex() {
        return bulletIndex >= bullets.size() || bulletIndex < 0;
    }

    @Override
    public void shoot() {
        getBullet().add(addShots, this);
    }
}