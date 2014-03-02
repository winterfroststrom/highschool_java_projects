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
public class WaveShot extends RecShot {

    public int increment;

    public WaveShot(SCraft s, int speed, int w, int h, int damage, int increment) {
        this(s, speed, w, h, damage, increment, 0, 1);
    }

    public WaveShot(SCraft s, int speed, int w, int h, int damage, int increment, double scalelr, double scaleud) {
        super(s, speed, w, h, damage, scalelr, scaleud);
        this.increment = increment;
    }

    @Override
    public void move() {
        super.move();
        this.w += increment;
        this.p.x -= increment / 2;
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawImage(Buffer.buff[8], (int) p.x - 8, (int) p.y - 8,
                (int) p.x + w + 8, (int) p.y + h + 8,
                0, 0, Buffer.buff[8].getWidth(null), Buffer.buff[8].getHeight(null), null);
    }

    @Override
    public WaveShot upgrade(SCraft s, int level) {
        switch (level) {
            case 1:
                return new WaveShot(s, 12, 4, 8, 5, 3);
            case 2:
                return new WaveShot(s, 12, 6, 8, 6, 3);
            case 3:
                return new WaveShot(s, 12, 10, 8, 12, 4);
            case 4:
                return new WaveShot(s, 12, 10, 8, 13, 4);
            case 5:
                return new WaveShot(s, 12, 12, 8, 13, 5);
            default:
                return new WaveShot(s, 12, 12, 8, 13, 5);
        }
    }

    @Override
    public int upgradeSPause(int level) {
        switch (level) {
            case 1:
                return 9;
            case 2:
                return 9;
            case 3:
                return 15;
            case 4:
                return 15;
            case 5:
                return 11;
            default:
                return 11;
        }
    }

    @Override
    public int upgradeSContinue(int level) {
        switch (level) {
            case 1:
                return 0;
            case 2:
                return 1;
            case 3:
                return 0;
            case 4:
                return 1;
            case 5:
                return 2;
            default:
                return 2;
        }
    }

    @Override
    public WaveShot getCopy(SCraft s) {
        return new WaveShot(s, speed, w, h, damage, increment, scalelr, scaleud);
    }
}
