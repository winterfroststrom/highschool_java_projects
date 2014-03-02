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
public class BAC extends ECraft {

    public BAC(Point p, ActionList actions, Main base) {
        super(p, actions, 3, 17, 17, base, 5);
    }

    public BAC(ActionList actions, Main base) {
        this(actions.point, actions, base);
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawImage(Buffer.buff[4], (int) p.x - 8, (int) p.y - 8,
                (int) p.x + w + 8, (int) p.y + h + 8,
                0, 0, Buffer.buff[4].getWidth(null), Buffer.buff[4].getHeight(null), null);
    }
}