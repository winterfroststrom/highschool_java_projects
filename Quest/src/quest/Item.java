/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package quest;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author Scalene
 */
public class Item extends Object{
    int time;

    public Item(Game game, int x,int y) {
        super(game,x,y,10,10);
        time = 60;
    }

    @Override
    public void add() {
        game.items.add(this);
    }

    @Override
    public void attack() {}

    @Override
    public boolean canAffect() {
        return false;
    }

    @Override
    public boolean isDead() {
        return time < 1;
    }

    @Override
    public void move() {}

    @Override
    public void paintSelf(Graphics2D g) {
        g.setColor(new Color(255,100,100,100));
        g.fill(self);
    }

    @Override
    public void reverseMove() {}

    @Override
    public void think() {}

    public void iterate(){
        time--;
    }

    public void affect(IObject o){
        o.damage(-5, null);
        time = 0;
    }
}
