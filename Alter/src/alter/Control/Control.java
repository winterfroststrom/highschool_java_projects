/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package alter.Control;

/**
 *
 * @author Scalene
 */

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
//import alter.Main;
import alter.Main.EventSystem;
import alter.Main.Event;
import java.util.LinkedList;

public class Control implements KeyListener, EventSystem{
    protected LinkedList<KeyEvent> keys;
    protected LinkedList<Event> events;
    protected Keysie keysie;

    public Control(){
        keysie = new Keysie(this);
        events = new LinkedList<Event>();
        keys = new LinkedList<KeyEvent>();
    }

    public void keyPressed(KeyEvent e) {keys.add(e);}

    public void keyReleased(KeyEvent e) {keys.add(e);}

    public void keyTyped(KeyEvent e) {}

    public LinkedList<Event> getEvents() {
        return events;
    }

    public void process(LinkedList<Event> queue){
        events.clear();
        keysie.process(queue);
        keys.clear();
    }
}
