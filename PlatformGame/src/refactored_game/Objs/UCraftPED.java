/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package refactored_game.Objs;

import refactored_game.Objs.SCraft;
import refactored_game.Objs.Craft;
import java.awt.Graphics2D;
import refactored_game.Buffer;
import refactored_game.Main;
import refactored_game.Objs.Attacks.Bullet;
import refactored_game.Objs.Attacks.PierceShot;
import refactored_game.Objs.Attacks.SplashShot;
import refactored_game.Objs.Attacks.WaveShot;
import refactored_game.support.Point;

/**
 *
 * @author Sparky
 */
public class UCraftPED extends SCraft {

    public int[] upgrades = new int[5];
    public int invincible = 0;

    public UCraftPED(Point p, Main base) {
        super(p, 4, 13, 13, base, 50);
        upgrades[0]++;
        dir = -1;
        addShots = base.pShots;
        shooting = true;
    }

    public void lShift() {
        bulletIndex--;
        if (isNotBulletIndex()) {
            bulletIndex = bullets.size() - 1;
        }
    }

    public void rShift() {
        bulletIndex++;
        if (isNotBulletIndex()) {
            bulletIndex = 0;
        }
    }

    @Override
    public void shoot() {
        super.shoot();
    }

    @Override
    public void move() {
        super.move();
        if (invincible > 0) {
            invincible--;
        }
    }

    @Override
    public void hit(int d) {
        if (invincible <= 0) {
            super.hit(d);
        }
    }

    @Override
    public int hit(Craft c) {
        if (invincible > 0) {
            return 0;
        }
        this.health -= c.collideDamage;
        invincible = 30;
        return collideDamage;
    }

    @Override
    public void draw(Graphics2D g) {
        if (invincible > 0 && invincible % 3 == 0) {
            return;
        }
        if (lr == 0) {
            g.drawImage(Buffer.buff[0], (int) p.x - 6, (int) p.y - 7,
                    (int) p.x + w + 7, (int) p.y + h + 7,
                    0, 0, Buffer.buff[0].getWidth(null), Buffer.buff[0].getHeight(null), null);
        } else if (lr < 0) {
            g.drawImage(Buffer.buff[1], (int) p.x - 7, (int) p.y - 7,
                    (int) p.x + w + 7, (int) p.y + h + 7,
                    0, 0, Buffer.buff[1].getWidth(null), Buffer.buff[1].getHeight(null), null);
        } else {
            g.drawImage(Buffer.buff[2], (int) p.x - 7, (int) p.y - 7,
                    (int) p.x + w + 7, (int) p.y + h + 7,
                    0, 0, Buffer.buff[2].getWidth(null), Buffer.buff[2].getHeight(null), null);
        }
    }

    public void upgrade(int num) {
        if (upgrades[num] >= 5) {
            return;
        }
        upgrades[num]++;
        switch (num) {
            case 0:
                bullets.set(num, bullets.get(num).upgrade(this, upgrades[num]));
                break;
            case 1:
                if (upgrades[num] < 2) {
                    bullets.add(new Bullet(getBullet().multiShot(2, this, 10, 7, 7, 2), 4, 0));
                } else {
                    bullets.set(num, bullets.get(num).upgradeMulti(upgrades[num] + 1, this, upgrades[num]));
                }
                break;
            case 2:
                if (upgrades[num] < 2) {
                    bullets.add(new Bullet(new WaveShot(this, 12, 4, 8, 5, 3), 9, 0));
                } else {
                    bullets.set(num, bullets.get(num).upgrade(this, upgrades[num]));
                }
                break;
            case 3:
                if (upgrades[num] < 2) {
                    bullets.add(new Bullet(new PierceShot(this, 20, 5, 15, 7), 20, 0));
                } else {
                    bullets.set(num, bullets.get(num).upgrade(this, upgrades[num]));
                }
                break;
            case 4:
                if (upgrades[num] < 2) {
                    bullets.add(new Bullet(new SplashShot(this, 4, 13, 13, 15, 25), 30, 0));
                } else {
                    bullets.set(num, bullets.get(num).upgrade(this, upgrades[num]));
                }
                break;
        }
    }
}
