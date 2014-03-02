/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package refactored_game.Objs.ECrafts;

import java.awt.Graphics2D;
import refactored_game.Buffer;
import refactored_game.Main;
import refactored_game.Objs.ECrafts.Actions.ActionList;
import refactored_game.support.Point;

/**
 *
 * @author Sparky
 */
public class IAC extends ECraft {

    public IAC(Point p, ActionList actions, Main base) {
        super(p, actions, 3, 15, 15, base, 15);
        for (int i = 0; i < 5; i++) {
            if (i == 1) {
                bullets.set(i, bullets.get(i).upgradeMulti(4, this, 3));
            } else {
                bullets.set(i, bullets.get(i).upgrade(this, 3));
            }
        }
    }

    public IAC(ActionList actions, Main base) {
        this(actions.point, actions, base);
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawImage(Buffer.buff[5], (int) p.x - 8, (int) p.y - 8,
                (int) p.x + w + 8, (int) p.y + h + 8,
                0, 0, Buffer.buff[5].getWidth(null), Buffer.buff[5].getHeight(null), null);
    }
}
