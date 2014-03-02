/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package alter.Model;

/**
 *
 * @author Scalene
 */
//import alter.Main;
import alter.Main.EventSystem;
import alter.Main.Event;
import java.util.LinkedList;

public class Model implements EventSystem{
    protected Logic logic;
    protected LinkedList<Event> events;

    public Model(){
        logic = new Logic(this);
        events = new LinkedList<Event>();
    }

    public LinkedList<Event> getEvents() {
        return events;
    }

    public void process(LinkedList<Event> queue){
        events.clear();
        logic.process(queue);
    }
}
