/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package refactored_game.Levels;

import refactored_game.support.CounterSet;
import refactored_game.Objs.ECrafts.ECraft;

/**
 *
 * @author Sparky
 */
public class Spawn extends CounterSet<ECraft> {

    public Spawn(int counter, ECraft e) {
        super(counter, e);
    }
}