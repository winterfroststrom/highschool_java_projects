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

public class Painter{
    protected View view;
    protected int mode;
    Mode_Start mode_start;

    public Painter(View view){
        this.view = view;
        mode_start = new Mode_Start(this);
    }

    public void paint(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 500, 400);
        switch(mode){
            case Event.START: mode_start.paint(g);
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

    public void process(LinkedList<Event> queue){
        switch(mode){
            case Event.START: mode_start.process(view.events,queue);
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