/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package refactored_game.Objs.ECrafts.Actions;

import refactored_game.support.CounterSet;
import refactored_game.Objs.ECrafts.Actions.Action;

/**
 *
 * @author Sparky
 */
public class tAction extends CounterSet<Action> {

    public int maxCounter;
    public double extra;
    public double extra2;

    public tAction(int counter, Action a) {
        super(counter, a);
        this.maxCounter = counter;
        this.extra = 0;
    }

    public tAction(int counter, Action a, double extra) {
        super(counter, a);
        this.maxCounter = counter;
        this.extra = extra;
    }

    public tAction(int counter, Action a, double extra, double extra2) {
        super(counter, a);
        this.maxCounter = counter;
        this.extra = extra;
        this.extra2 = extra2;
    }

    @Override
    public tAction getCopy() {
        return new tAction(maxCounter, event, extra, extra2);
    }
}
