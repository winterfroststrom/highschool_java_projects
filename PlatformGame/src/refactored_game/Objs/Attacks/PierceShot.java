/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package refactored_game.Objs.Attacks;

import java.awt.Graphics2D;
import refactored_game.Buffer;
import refactored_game.Objs.SCraft;

/**
 *
 * @author Sparky
 */
public class PierceShot extends RecShot {

    public PierceShot(SCraft s, int speed, int w, int h, int damage, double scalelr, double scaleud) {
        super(s, speed, w, h, damage, scalelr, scaleud);
    }

    public PierceShot(SCraft s, int speed, int w, int h, int damage) {
        this(s, speed, w, h, damage, 0, 1);
    }

    @Override
    public PierceShot getCopy(SCraft s) {
        return new PierceShot(s, speed, w, h, damage, scalelr, scaleud);
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawImage(Buffer.buff[9], (int) p.x - 10, (int) p.y - 8,
                (int) p.x + w + 10, (int) p.y + h + 8,
                0, 0, Buffer.buff[9].getWidth(null), Buffer.buff[9].getHeight(null), null);
    }

    @Override
    public boolean isDead() {
        return false;
    }

    @Override
    public PierceShot upgrade(SCraft s, int level) {
        switch (level) {
            case 1:
                return new PierceShot(s, 20, 5, 15, 7);
            case 2:
                return new PierceShot(s, 20, 5, 15, 15);
            case 3:
                return new PierceShot(s, 20, 5, 15, 20);
            case 4:
                return new PierceShot(s, 20, 5, 15, 25);
            case 5:
                return new PierceShot(s, 20, 5, 15, 30);
            default:
                return new PierceShot(s, 20, 5, 15, 30);
        }
    }

    @Override
    public int upgradeSPause(int level) {
        switch (level) {
            case 1:
                return 20;
            case 2:
                return 19;
            case 3:
                return 17;
            case 4:
                return 15;
            case 5:
                return 12;
            default:
                return 12;
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
                return 1;
            default:
                return 1;
        }
    }
}
