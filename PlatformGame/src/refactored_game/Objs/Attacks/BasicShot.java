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
public class BasicShot extends RecShot {

    public BasicShot(SCraft s, int speed, int w, int h, int damage, double scalelr, double scaleud) {
        super(s, speed, w, h, damage, scalelr, scaleud);
    }

    public BasicShot(SCraft s, int speed, int w, int h, int damage) {
        super(s, speed, w, h, damage, 0, 1);
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawImage(Buffer.buff[7], (int) p.x - 2, (int) p.y - 2,
                (int) p.x + w + 2, (int) p.y + h + 2,
                0, 0, Buffer.buff[7].getWidth(null), Buffer.buff[7].getHeight(null), null);
    }

    @Override
    public BasicShot getCopy(SCraft s) {
        return new BasicShot(s, speed, w, h, damage, scalelr, scaleud);
    }

    @Override
    public BasicShot upgrade(SCraft s, int level) {
        switch (level) {
            case 1:
                return new BasicShot(s, 10, 7, 7, 5);
            case 2:
                return new BasicShot(s, 11, 7, 7, 7);
            case 3:
                return new BasicShot(s, 12, 7, 7, 7);
            case 4:
                return new BasicShot(s, 13, 7, 7, 7);
            case 5:
                return new BasicShot(s, 14, 7, 7, 7);
            default:
                return new BasicShot(s, 16, 7, 7, 9);
        }
    }

    @Override
    public int upgradeSPause(int level) {
        switch (level) {
            case 1:
                return 6;
            case 2:
                return 7;
            case 3:
                return 7;
            case 4:
                return 7;
            case 5:
                return 8;
            default:
                return 10;
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
                return 1;
            case 4:
                return 1;
            case 5:
                return 2;
            default:
                return 2;
        }
    }
}
