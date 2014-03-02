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

public class Mode_Start implements Mode{
    Keysie keysie;
    private int screen;

    public Mode_Start(Keysie keysie){
        this.keysie = keysie;
        screen = Event.TITLE_SCREEN;
    }

    public void process(LinkedList<KeyEvent> k,LinkedList<Event> e){
        for(KeyEvent ke:k){
            if(ke.getID() == KeyEvent.KEY_PRESSED){
                press(ke,e);
            } else if(ke.getID() == KeyEvent.KEY_RELEASED){
                release(ke,e);
            }
        }
    }

    private void press(KeyEvent k,LinkedList<Event> e){
        switch(k.getKeyCode()){
            case KeyEvent.VK_SPACE:
                if(screen == Event.TITLE_SCREEN){
                    e.add(new Event(Event.TRANSITION,Event.FILE_SCREEN));
                    screen = Event.FILE_SCREEN;
                }
                break;
            case KeyEvent.VK_UP:
                break;
            case KeyEvent.VK_DOWN:
                break;
            case KeyEvent.VK_A:
                if(screen == Event.FILE_SCREEN){
                    e.add(new Event(Event.TRANSITION,Event.TITLE_SCREEN));
                    screen = Event.TITLE_SCREEN;
                }
                break;
            case KeyEvent.VK_S: 
                break;
        }
    }

    private void release(KeyEvent k,LinkedList<Event> e){
        switch(k.getKeyCode()){
            
        }
    }

    public void reset(){
        screen = Event.TITLE_SCREEN;
    }
}
