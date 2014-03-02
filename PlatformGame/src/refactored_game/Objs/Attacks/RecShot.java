/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package refactored_game.Objs.Attacks;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import refactored_game.Objs.SCraft;
import refactored_game.interfaces.Drawable;

/**
 *
 * @author Sparky
 */
public abstract class RecShot extends Shot implements Drawable {

    public int w;
    public int h;

    public RecShot(SCraft s, int speed, int w, int h, int damage, double scalelr, double scaleud) {
        super(s, speed, damage, scalelr, scaleud);
        this.w = w;
        this.h = h;
        modifyPoint(s);
    }

    @Override
    public Shape construct() {
        return new Rectangle2D.Double(p.x, p.y, w, h);
    }

    public abstract RecShot getCopy(SCraft s);

    public abstract RecShot upgrade(SCraft s, int level);

    public abstract int upgradeSPause(int level);

    public abstract int upgradeSContinue(int level);

    @Override
    public void modifyPoint(SCraft s) {
        p.x += (s.w / 2 - this.w / 2);
        p.y += (s.h / 2 - this.h / 2);
    }
}
