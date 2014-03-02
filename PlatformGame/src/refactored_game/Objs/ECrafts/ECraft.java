/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package refactored_game.Objs.ECrafts;

import refactored_game.Objs.ECrafts.Actions.ActionList;
import refactored_game.Main;
import refactored_game.Objs.Attacks.Bullet;
import refactored_game.Objs.Attacks.PierceShot;
import refactored_game.Objs.Attacks.SplashShot;
import refactored_game.Objs.Attacks.WaveShot;
import refactored_game.support.Point;
import refactored_game.Objs.SCraft;
import refactored_game.Objs.ECrafts.Actions.tAction;

/**
 *
 * @author Sparky
 */
public abstract class ECraft extends SCraft {

    public ActionList actions;
    public boolean targeting = false;
    public int index = 0;

    public ECraft(Point p, ActionList actions, int speed, int w, int h, Main base, int health) {
        super(p, speed, w, h, base, health);
        this.actions = actions;
        bullets.add(new Bullet(getBullet().multiShot(2, this, 10, 7, 7, 2), 4, 0));
        bullets.add(new Bullet(new WaveShot(this, 12, 4, 8, 5, 3), 9, 0));
        bullets.add(new Bullet(new PierceShot(this, 20, 5, 15, 7), 20, 0));
        bullets.add(new Bullet(new SplashShot(this, 4, 13, 13, 15, 25), 30, 0));
    }

    public ECraft(Point p, ActionList actions, int speed, int w, int h, Main base) {
        this(p, actions, speed, w, h, base, 5);
    }

    public ECraft(ActionList actions, int speed, int w, int h, Main base) {
        this(actions.point, actions, speed, w, h, base, 5);
    }

    public ECraft(ActionList actions, int speed, int w, int h, Main base, int health) {
        this(actions.point, actions, speed, w, h, base, health);
    }

    @Override
    public void shoot() {
        if (targeting) {
            getBullet().setAngle(moveToX(base.player.p.x, base.player.p.y), moveToY(base.player.p.x, base.player.p.y));
        }
        getBullet().add(addShots, this);
    }

    @Override
    public void move() {
        super.move();
        if (index > -1 && index < actions.size()) {
            tAction ta = actions.get(index);
            ta.counter--;
            while (index > -1 && index < actions.size() && actions.get(index).counter <= 0) {
                ta = actions.get(index);
                ta.counter = ta.maxCounter;
                index++;
                switch (ta.event) {
                    case turn:
                        lr = ta.extra;
                        ud = ta.extra2;
                        break;
                    case loop:
                        index = (int) ta.extra;
                        break;
                    case stop:
                        index = -1;
                        break;
                    case bullet:
                        if (isBulletIndex((int) ta.extra)) {
                            bulletIndex = (int) ta.extra;
                        }
                        break;
                    case shoot:
                        shooting = ta.extra == 1;
                        break;
                    case angle:
                        getBullet().setAngle(ta.extra, ta.extra2);
                        break;
                    case frequency:
                        getBullet().setFrequency(ta.extra, ta.extra2);
                        break;
                    case speed:
                        this.speed = (int) ta.extra;
                        break;
                    case moveTo:
                        this.moveTo(ta.extra, ta.extra2);
                        break;
                    case atPlace:
                        ta.counter = 1;
                        if (p.distance(ta.extra, ta.extra2) > Math.pow(w, 2)) {
                            index--;
                        }
                        break;
                    case target:
                        targeting = ta.extra == 1;
                        break;
                }
            }
        }
    }

    public boolean isBoss() {
        return false;
    }
}
