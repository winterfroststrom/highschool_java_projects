/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package refactored_game.Objs.Attacks;

import java.awt.Graphics2D;
import refactored_game.Buffer;
import refactored_game.Objs.Craft;
import refactored_game.Objs.Explosion;
import refactored_game.Objs.SCraft;

/**
 *
 * @author Sparky
 */
public class SplashShot extends RecShot {
    public int radius;
    public boolean exploded = false;

    public SplashShot(SCraft s, int speed, int w, int h, int damage, int radius) {
        this(s, speed, w, h, damage, radius, 0, 1);
    }

    public SplashShot(SCraft s, int speed, int w, int h, int damage, int radius, double scalelr, double scaleud) {
        super(s, speed, w, h, damage, scalelr, scaleud);
        this.radius = radius;
    }

    @Override
    public int hit(Craft c) {
        hit = true;
        this.w += radius;
        this.h += radius;
        this.p.x -= radius / 2;
        this.p.y -= radius / 2;
        this.speed = 0;
        return damage;
    }

    @Override
    public void move() {
        super.move();
        if (hit) {
            exploded = true;
            base.explosions.add(new Explosion(p, w, h, base));
        }
    }

    @Override
    public boolean isDead() {
        return exploded;
    }

    @Override
    public SplashShot getCopy(SCraft s) {
        return new SplashShot(s, speed, w, h, damage, radius, scalelr, scaleud);
    }

    @Override
    public SplashShot upgrade(SCraft s, int level) {
        switch (level) {
            case 1:
                return new SplashShot(s, 4, 13, 13, 15, 25);
            case 2:
                return new SplashShot(s, 4, 14, 14, 15, 27);
            case 3:
                return new SplashShot(s, 4, 14, 14, 15, 32);
            case 4:
                return new SplashShot(s, 5, 15, 15, 16, 34);
            case 5:
                return new SplashShot(s, 5, 16, 16, 18, 37);
            default:
                return new SplashShot(s, 7, 18, 18, 20, 40);
        }
    }

    @Override
    public int upgradeSPause(int level) {
        switch (level) {
            case 1:
                return 30;
            case 2:
                return 28;
            case 3:
                return 26;
            case 4:
                return 23;
            case 5:
                return 21;
            default:
                return 15;
        }
    }

    @Override
    public int upgradeSContinue(int level) {
        switch (level) {
            case 1:
                return 0;
            case 2:
                return 0;
            case 3:
                return 0;
            case 4:
                return 0;
            case 5:
                return 0;
            default:
                return 0;
        }
    }

    @Override
    public void draw(Graphics2D g) {
        if (!hit) {
            g.drawImage(Buffer.buff[10], (int) p.x - 5, (int) p.y - 5,
                    (int) p.x + w + 5, (int) p.y + h + 5,
                    0, 0, Buffer.buff[10].getWidth(null), Buffer.buff[10].getHeight(null), null);
        }
    }
}