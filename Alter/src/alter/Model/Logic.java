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

public class Logic {
    Model model;
    protected int mode;
    Mode_Start mode_start;
    Mode_World mode_world;

    public Logic(Model model){
        this.model = model;
        mode_start = new Mode_Start(this);
        mode_world = new Mode_World(this);
    }

    public void process(LinkedList<Event> queue){
        switch(mode){
            case Event.START: mode_start.process(model.events,queue);
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
