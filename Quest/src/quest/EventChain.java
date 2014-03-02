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

public class EventChain extends Event{
    ArrayList<Event> events;
    int areaid;
    int id;

    public EventChain(Game game, int x,int y,int w,int h, int target, ArrayList<Event> input,int areaid,int id) {
        this(game,x,y,w,h,target,input,0,areaid,id);
    }

    public EventChain(Game game, int x,int y,int w,int h, int target, ArrayList<Event> input,int timer,int areaid,int id) {
        super(game,x,y,w,h,target,timer);
        events = input;
        this.areaid = areaid;
        this.id = id;
    }

    @Override
    public void event(ListIterator<Event> it) {
        if(timer-- > 0)return;
        for(Event e: events){it.add(e);}
        game.ga.getAreaById(areaid).vars.set(id, Boolean.TRUE);
        use();
    }
}
