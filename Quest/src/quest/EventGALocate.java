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

public class EventGALocate extends Event{
    GrandArea ga;

    public EventGALocate(Game game, int x,int y,int w,int h, int target, GrandArea ga) {
        this(game,x,y,w,h,target, ga,0);
    }

    public EventGALocate(Game game, int x,int y,int w,int h, int target, GrandArea ga,int timer) {
        super(game,x,y,w,h,target,timer);
        this.ga = ga;
    }

    @Override
    public void event(ListIterator<Event> it) {
        if(timer-- > 0)return;
        ga.loadGA();
        use();
    }
}

