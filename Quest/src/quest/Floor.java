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

public class Floor extends Object{
    Rectangle affect;
    Element carry;

    public Floor(Game game,int x,int y,int width,int height){
        super(game,x,y,width,height);
        affect = new Rectangle(x+10,y+10,width-20,height-20);
        carry = Element.fire;
    }

    @Override
    public void add() {
        game.floor.add(this);
    }

    @Override
    public void think() {}

    @Override
    public boolean canAffect() {
        return true;
    }

    public void affect(IObject o){
        if(o.self.intersects(affect)&&!o.platform){
            o.damage(2,carry);
            if(o != game.player)
                o.dead = true;
            else
//                game.area.positionPlayer();
                game.ga.current.positionPlayer();
        }
    }

    @Override
    public void reverseMove() {}

    @Override
    public void attack() {}

    @Override
    public boolean isDead() {
        return false;
    }


    @Override
    public void move() {}

    @Override
    public void paintSelf(Graphics2D g) {
        g.setColor(new Color(0,255,255,100));
        g.fill(self);
        g.fill(affect);
    }
}
