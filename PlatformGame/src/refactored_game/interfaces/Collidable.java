/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package refactored_game.interfaces;

import java.awt.Shape;
import refactored_game.support.Line;

/**
 *
 * @author Sparky
 */
public interface Collidable {
    public Shape construct();
    public void move();
    public Line getPath();
}
