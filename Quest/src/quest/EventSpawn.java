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

public class EventSpawn extends Event{
    ArrayList<NPC> spawns;

    public EventSpawn(Game game, int x,int y,int w,int h, int target, ArrayList<NPC> input) {
        this(game,x,y,w,h,target,input,0);
    }

    public EventSpawn(Game game, int x,int y,int w,int h, int target, ArrayList<NPC> input,int timer) {
        super(game,x,y,w,h,target,timer);
        spawns = input;
    }

    @Override
    public void event(ListIterator<Event> it) {
        if(timer-- > 0)return;
        for(NPC n:spawns)
            n.add();
        use();
    }
}
