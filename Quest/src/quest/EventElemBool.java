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

public class EventElemBool extends EventElement{

    public EventElemBool(Game game, int x,int y,int w,int h, Element input) {
        this(game,x,y,w,h,input,0);
    }

    public EventElemBool(Game game, int x,int y,int w,int h, Element input,int timer) {
        super(game,x,y,w,h,input,timer);
    }

    @Override
    public void sense(){
        for(Block b:game.blocks){
            if(b.canCarry&& b.isCarry && b.carry.equals(elem) && b.self.intersects(trigger))
                activate();
        }
    }

    @Override
    public void event(ListIterator<Event> it) {
        if(timer-- > 0)return;
        game.ev.torched = true;
        use();
    }
}
