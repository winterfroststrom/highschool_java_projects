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

public class Keysie{
    protected Control control;
    protected int mode;
    Mode_Start mode_start;
    private boolean disable;
    private int disableCount;

    public Keysie(Control control){
        this.control = control;
        mode = Event.START;
        mode_start = new Mode_Start(this);
        disable = false;
        disableCount = 0;
    }

    public void process(LinkedList<Event> queue){
        for(Event e:queue){
            if(e.getID().equals(Event.MODE)){
                mode = e.getEvent();
            } else if(e.getID().equals(Event.DISABLEC)){
                disable = true;
                disableCount = e.getEvent();
            }
        }
        if(disable && disableCount > 0){
            control.events.add(new Event(Event.DISABLEDC,disableCount));
            disableCount--;
            return;
        }
        switch(mode){
            case Event.START: mode_start.process(control.keys,control.events);
                break;
            case Event.PAUSE:
                break;
            case Event.WORLD:
                break;
            case Event.BATTLE:
                break;
            case Event.SCENE:
                break;
        }
    }
}
