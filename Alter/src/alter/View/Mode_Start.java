/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package alter.View;

/**
 *
 * @author Scalene
 */

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.LinkedList;
import alter.Main.Event;

public class Mode_Start implements Mode{
    Painter painter;
    private int screen;
    private int trans;
    private final int transTime = 40;
    
    public Mode_Start(Painter painter){
        this.painter = painter;
        screen = Event.TITLE_SCREEN;
        trans = 0;
    }

    public void paint(Graphics2D g) {
        if(trans > 0)trans--;
        switch(screen){
            case Event.TITLE_SCREEN: paintTitle(g);
                break;
            case Event.FILE_SCREEN: paintFile(g);
                break;
        }
    }

    private void paintTitle(Graphics2D g){
        g.setColor(new Color(0,0,0,255*(transTime-trans)/transTime));
        g.fillRect(100, 100, 300, 150);
        if(trans > 0){
            g.setColor(new Color(0,0,0,255*(trans)/transTime));
            g.fillRect(150, 50, 200, 75);
            g.fillRect(150, 150, 200, 75);
            g.fillRect(150, 250, 200, 75);
        } else {
            g.fillRect(200, 300, 100, 25);
        }
    }

    private void paintFile(Graphics2D g){
        g.setColor(new Color(0,0,0,255*(transTime-trans)/transTime));
        g.fillRect(150, 50, 200, 75);
        g.fillRect(150, 150, 200, 75);
        g.fillRect(150, 250, 200, 75);
        if(trans > 0){
            g.setColor(new Color(0,0,0,255*(trans)/transTime));
            g.fillRect(100, 100, 300, 150);
        } else {
            g.fillRect(150, 350, 80, 25);
            g.fillRect(270, 350, 80, 25);
        }
    }

    public void process(LinkedList<Event> events,LinkedList<Event> q){
        for(Event e:q){
            if(e.getID().equals(Event.TRANSITION)){
                screen = e.getEvent();
                events.add(new Event(Event.DISABLEC,transTime));
                trans = transTime;
            }
        }
    }

    public void reset() {
        screen = Event.TITLE_SCREEN;
    }
}
