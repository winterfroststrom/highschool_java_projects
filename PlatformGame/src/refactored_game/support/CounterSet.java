/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package refactored_game.support;

/**
 *
 * @author Sparky
 */
public class CounterSet<T> {

    public int counter;
    public T event;

    public CounterSet(int counter, T event) {
        this.counter = counter;
        this.event = event;
    }

    public CounterSet<T> getCopy() {
        return new CounterSet(counter, event);
    }
}
