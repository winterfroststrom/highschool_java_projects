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
public class Wall extends Object{

    public Wall(Game game,int x,int y,int width,int height){
        super(game,x,y,width,height);
    }

    @Override
    public void add() {
        game.walls.add(this);
    }

    @Override
    public void think() {}

    @Override
    public boolean canAffect() {
        return false;
    }

    @Override
    public void reverseMove() {}

    @Override
    public void attack() {
        
    }

    @Override
    public boolean isDead() {
        return false;
    }


    @Override
    public void move() {}

    @Override
    public void paintSelf(Graphics2D g) {
        g.setColor(new Color(255,255,255,100));
        g.fill(self);
    }
}
