/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package refactored_game.Objs.Walls;

import refactored_game.Main;
import refactored_game.support.Point;

/**
 *
 * @author Sparky
 */
public class DWall extends BorderWall {

    public DWall(Main base) {
        super(new Point(0, 500), 500, 3, base);
    }
}
