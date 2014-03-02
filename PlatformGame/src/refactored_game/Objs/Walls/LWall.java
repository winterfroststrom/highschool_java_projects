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
public class LWall extends BorderWall {

    public LWall(Main base) {
        super(new Point(0, 0), 8, 500, base);
        collideDamage = 3;
    }
}
