/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package alter.Model;

/**
 *
 * @author Scalene
 */

import java.util.LinkedList;
import alter.Main.Event;

public interface Mode {
    public void process(LinkedList<Event> e,LinkedList<Event> q);
    public void reset();
}
