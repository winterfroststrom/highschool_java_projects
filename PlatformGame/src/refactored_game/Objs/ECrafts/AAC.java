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
public class AAC extends ECraft {

    public AAC(Point p, ActionList actions, Main base) {
        super(p, actions, 3, 13, 13, base, 30);
        for (int i = 0; i < 5; i++) {
            if (i == 1) {
                bullets.set(i, bullets.get(i).upgradeMulti(6, this, 5));
            } else {
                bullets.set(i, bullets.get(i).upgrade(this, 5));
            }
        }
    }

    public AAC(ActionList actions, Main base) {
        this(actions.point, actions, base);
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawImage(Buffer.buff[6], (int) p.x - 8, (int) p.y - 8,
                (int) p.x + w + 8, (int) p.y + h + 8,
                0, 0, Buffer.buff[6].getWidth(null), Buffer.buff[6].getHeight(null), null);
    }
}
