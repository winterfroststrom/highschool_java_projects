/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package alter.Control;

/**
 *
 * @author Scalene
 */

import java.util.LinkedList;
import alter.Main.Event;
import java.awt.event.KeyEvent;

public interface Mode {
    public void process(LinkedList<KeyEvent> k,LinkedList<Event> e);
    public void reset();
}
