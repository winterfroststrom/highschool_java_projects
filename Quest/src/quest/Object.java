/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package quest;

/**
 *
 * @author Scalene
 */
import java.awt.Rectangle;
import java.awt.Graphics2D;

public abstract class Object {
    Rectangle self;
    Rectangle previous;
    Game game;
    boolean attack;
    double density;

    public Object(Game game,int x, int y,int width, int height){
        self = new Rectangle(x,y,width,height);
        previous = new Rectangle(x,y,width,height);
        this.game = game;
        density = 1;
    }

    public abstract void reverseMove();

    public abstract boolean canAffect();
    
    public abstract void move();

    public void movedx(int deltax){
        self.x += deltax;
    }
    public boolean canAttack(){
        if(attack){
            attack = false;
            return true;
        }
        return false;
    }

    public abstract void attack();

    public void movedy(int deltay){
        self.y += deltay;
    }

    public abstract void think();

    public abstract boolean isDead();

    public abstract void add();

    public void pCollide(Object o){
        int dpxp = (previous.x + self.width/2) - (o.previous.x + o.previous.width/2);
        int dpyp = (previous.y + self.height/2) - (o.previous.y + o.previous.height/2);
        int dxp = (self.x + self.width/2) - (o.self.x + o.self.width/2);
        int dyp = (self.y + self.height/2) - (o.self.y + o.self.height/2);
        int dx = o.self.width/2+self.width/2;
        int dy = o.self.height/2+self.height/2;
        if(Math.abs((double)dpxp)/dx > Math.abs((double)dpyp)/dy){
            if(dpxp > 0) this.movedx(dx - dxp);
            else this.movedx(-(dx + dxp));
        } else {
            if(dpyp > 0) this.movedy(dy - dyp);
            else this.movedy(-(dy + dyp));
        }
    }

    public abstract void paintSelf(Graphics2D g);
}
