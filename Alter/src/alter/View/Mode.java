/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package alter.View;

/**
 *
 * @author Scalene
 */
import java.awt.Graphics2D;
import java.util.LinkedList;
import alter.Main.Event;

public interface Mode {
    public void paint(Graphics2D g);
    public void process(LinkedList<Event> events,LinkedList<Event> queue);
    public void reset();
}
