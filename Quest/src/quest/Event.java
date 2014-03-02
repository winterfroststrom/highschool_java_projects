/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package quest;

/**
 *
 * @author Scalene
 */
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.util.ListIterator;

public abstract class Event {
    Game game;
    private boolean activated;
    private boolean used;
    Rectangle trigger;
    int target;

    static final int tarPlayer = 0;
    static final int tarALL = 1;
    static final int tarNPC = 2;
    static final int tarPlayerAb = 3;
    static final int tarALLAb = 4;
    static final int tarNPCAb = 5;
    static final int tarBlock = 6;
    static final int tarBlockAb = 7;
    static final int tarElement = 8;
    static final int tarMulti = 8;
    int timer;

    public Event(Game game, int x,int y,int w,int h, int target,int timer) {
        this.game = game;
        trigger = new Rectangle(x,y,w,h);
        this.target = target;
        activated = false;
        this.timer = timer;
    }

    public void add(){
        game.events.add(this);
    }

    public void sense(){
        switch(target){
            case tarPlayer: if(game.player.self.intersects(trigger)) activated = true;
                break;
            case tarALL: if(game.player.self.intersects(trigger)){ activated = true; break;}
                break;
            case tarNPC: for(NPC n:game.npcs){if(n.self.intersects(trigger)){activated = true;break;}}
                break;
            case tarPlayerAb: if(!game.player.self.intersects(trigger)) activated = true;
                break;
            case tarALLAb: if(game.player.self.intersects(trigger)){break;}
            case tarNPCAb: activated = true;
                            for(NPC n:game.npcs){
                                if(n.self.intersects(trigger)){activated = false;break;}
                            }
                break;
            case tarBlock: for(Block b:game.blocks)if(b.self.intersects(trigger)){activated = true;break;}
                break;
            case tarBlockAb: activated = true; for(Block b:game.blocks)if(b.self.intersects(trigger)){activated = false;break;}
                break;
        }
    }

    public void activate() {
        this.activated = true;
    }


    public boolean isActivated(){
        return activated;
    }

    public abstract void event(ListIterator<Event> it);

    public boolean isUsed(){
        return used;
    }

    public void use(){
         used = true;
    }

    public void paintSelf(Graphics2D g) {
        g.setColor(new Color(255,200,255,100));
        g.fill(trigger);
    }
}
