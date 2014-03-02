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
public class Mode_Start implements Mode{
    Logic logic;
    
    public Mode_Start(Logic logic){
        this.logic = logic;
    }

    public void process(LinkedList<Event> ev,LinkedList<Event> qu) {
        for(Event q:qu){
            if(q.getID().equals(Event.LOAD)){
                
            } else {
                ev.add(new Event(q.getID(),q.getEvent()));
            }
        }
    }

    public void reset(){
        
    }
}
