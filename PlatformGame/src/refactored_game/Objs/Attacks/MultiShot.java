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
public class MultiShot extends RecShot {

    public MultiShot(SCraft s, int speed, int w, int h, int damage, double scalelr, double scaleud) {
        super(s, speed, w, h, damage, scalelr, scaleud);
    }

    public MultiShot(SCraft s, int speed, int w, int h, int damage) {
        this(s, speed, w, h, damage, 0, 1);
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawImage(Buffer.buff[18], (int) p.x - 6, (int) p.y - 6,
                (int) p.x + w + 6, (int) p.y + h + 6,
                0, 0, Buffer.buff[18].getWidth(null), Buffer.buff[18].getHeight(null), null);
    }

    @Override
    public MultiShot getCopy(SCraft s) {
        return new MultiShot(s, speed, w, h, damage, scalelr, scaleud);
    }

    @Override
    public MultiShot upgrade(SCraft s, int level) {
        switch (level) {
            case 1:
                return new MultiShot(s, 10, 7, 7, 2);
            case 2:
                return new MultiShot(s, 12, 7, 7, 2);
            case 3:
                return new MultiShot(s, 14, 8, 8, 3);
            case 4:
                return new MultiShot(s, 16, 8, 8, 3);
            case 5:
                return new MultiShot(s, 18, 9, 9, 4);
            default:
                return new MultiShot(s, 18, 9, 9, 2);
        }
    }

    @Override
    public int upgradeSPause(int level) {
        switch (level) {
            case 1:
                return 4;
            case 2:
                return 4;
            case 3:
                return 4;
            case 4:
                return 4;
            case 5:
                return 4;
            default:
                return 4;
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
}
