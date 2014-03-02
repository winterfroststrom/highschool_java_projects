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
public class UWall extends BorderWall {

    public UWall(Main base) {
        super(new Point(-5, -5), 500, 3, base);
    }
}
