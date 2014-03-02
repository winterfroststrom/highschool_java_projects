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
import java.awt.Graphics2D;

public class Platform extends Floor{
    int speed;
    int x;
    int x2;
    int y;
    int y2;
    double theta;
    boolean forward;
    int pause;

    public Platform(Game game,int x,int y,int width,int height,int x2,int y2,int speed){
        super(game,x,y,width,height);
        forward = true;
        pause = 10;
        this.x = x;
        this.y = y;
        this.x2 = x2;
        this.y2 = y2;
        this.speed = speed;
        theta = Math.atan2(y-y2, x2-x);
    }

    @Override
    public void add() {
        game.floor.add(0,this);
    }

    @Override
    public void think() {
        if(--pause > 0)
            return;
        if(forward){
            if(Math.pow(self.x-x2,2) + Math.pow(self.y-y2,2) < 10){
                forward = false;
                theta -= Math.PI;
                pause = 10;
            }
        } else {
            if(Math.pow(self.x-x,2) + Math.pow(self.y-y,2) < 10){
                forward = true;
                theta += Math.PI;
                pause = 10;
            }
        }
    }

    @Override
    public boolean canAffect() {
        return true;
    }

    @Override
    public void affect(IObject o){
        o.platform = true;
        if(pause < 1){
            o.movedx((int)(speed*Math.cos(theta)));
            o.movedy((int)(speed*Math.sin(theta)));
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
    public void move() {
        if(pause < 1){
            this.movedx((int)(speed*Math.cos(theta)));
            this.movedy((int)(speed*Math.sin(theta)));
        }
    }

    @Override
    public void paintSelf(Graphics2D g) {
        g.setColor(new Color(55,55,55,100));
        g.fill(self);
    }
}
