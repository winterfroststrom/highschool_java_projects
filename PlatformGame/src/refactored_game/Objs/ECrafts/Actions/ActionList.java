/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package refactored_game.Objs.ECrafts.Actions;

import java.util.ArrayList;
import refactored_game.support.Point;

/**
 *
 * @author Sparky
 */
public class ActionList extends ArrayList<tAction> {

    public Point point;

    public ActionList(Point p) {
        super();
        point = p;
    }

    public ActionList() {
        super();
        point = new Point(0, 0);
    }

    public ActionList getCopy() {
        ActionList copy = new ActionList(point);
        for (tAction a : this) {
            copy.add(a.getCopy());
        }
        return copy;
    }
}
