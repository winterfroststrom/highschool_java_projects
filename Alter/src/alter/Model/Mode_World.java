/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package alter.Model;

import alter.Main.Event;
import java.util.LinkedList;

/**
 *
 * @author Scalene
 */
public class Mode_World implements Mode{
    Logic logic;

    public Mode_World(Logic logic) {
        this.logic = logic;
    }

    public void process(LinkedList<Event> e,LinkedList<Event> q) {
        
    }


    public void reset() {
        
    }
}
