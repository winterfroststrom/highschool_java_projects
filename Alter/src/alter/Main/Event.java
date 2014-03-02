/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package alter.Main;

/**
 *
 * @author Scalene
 */
public class Event {
    public static final String TRANSITION = "transition";
        public static final int TITLE_SCREEN = 0;
        public static final int FILE_SCREEN = 1;
    public static final String DISABLEC = "disableC";
    public static final String DISABLEDC = "disabledC";
    public static final String MODE = "mode";
        public static final int START = 0;
        public static final int PAUSE = 1;
        public static final int WORLD = 2;
        public static final int BATTLE = 3;
        public static final int SCENE = 4;
    public static final String LOAD = "load";

    public Event(String id, int event) {
        this.id = id;
        this.event = event;
    }


    public String getID(){
        return id;
    }

    public int getEvent(){
        return event;
    }
    
    private String id;
    private int event;
}
