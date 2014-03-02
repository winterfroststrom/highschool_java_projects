/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package refactored_game.Levels;

import refactored_game.Objs.Craft;
import refactored_game.support.CounterSet;

/**
 *
 * @author Sparky
 */
public class Enviroment extends CounterSet<Craft> {

    public Enviroment(int counter, Craft c) {
        super(counter, c);
    }
}
