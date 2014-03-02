/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package quest;

/**
 *
 * @author Scalene
 */
import java.util.ListIterator;

public class EventLocate extends Event{
    Area area;

    public EventLocate(Game game, int x,int y,int w,int h, int target, int input) {
        this(game,x,y,w,h,target,input,0);
    }

    public EventLocate(Game game, int x,int y,int w,int h, int target, int input,int timer) {
        super(game,x,y,w,h,target,timer);
        this.area = game.ga.getAreaById(input);
    }

    @Override
    public void event(ListIterator<Event> it) {
        if(timer-- > 0)return;
        area.loadArea();
        use();
    }
}
