/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package alter.Main;

/**
 *
 * @author Scalene
 */
import java.util.LinkedList;

public interface EventSystem {
    public LinkedList<Event> getEvents();
    public void process(LinkedList<Event> queue);
}
