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

public class EventBind extends Event{
    IObject io;

    public EventBind(Game game, int x,int y,int w,int h, int target, IObject io) {
        this(game,x,y,w,h,target,io,0);
    }

    public EventBind(Game game, int x,int y,int w,int h, int target,IObject io,int timer) {
        super(game,x,y,w,h,target,timer);
        this.io = io;
    }

    @Override
    public void event(ListIterator<Event> it) {
        if(timer-- > 0)return;
        io.dead = true;
        use();
    }
}
