/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package quest;

/**
 *
 * @author Scalene
 */
import java.util.ArrayList;
import java.util.ListIterator;
import java.awt.Color;
import java.awt.Graphics2D;

public class EventMulti extends Event{
    ArrayList<Event> events;
    Event use;
    int areaid;
    int id;

    public EventMulti(Game game, ArrayList<Event> input,Event use,int areaid,int id) {
        this(game,input,0,use,areaid,id);
    }

    public EventMulti(Game game, ArrayList<Event> input,int timer,Event use,int areaid,int id) {
        super(game,0,0,0,0,tarMulti,timer);
        events = input;
        this.use = use;
        this.areaid = areaid;
        this.id = id;
    }

    @Override
    public void sense(){
        for(Event e:events){
            e.sense();
            if(!e.isActivated())
                return;
        }
        activate();
    }

    @Override
    public void paintSelf(Graphics2D g) {
        g.setColor(new Color(255,200,255,100));
        for(Event e:events)
            e.paintSelf(g);
    }

    @Override
    public void event(ListIterator<Event> it) {
        if(timer-- > 0)return;
        game.ga.getAreaById(areaid).vars.set(id, Boolean.TRUE);
        it.add(use);
        use();
    }
}
