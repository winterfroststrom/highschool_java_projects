/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package quest;

/**
 *
 * @author Scalene
 */
import java.awt.geom.AffineTransform;

public class Arrow extends Attack{
    int time;
    int push;
    double theta;
    int damage;

    public Arrow(IObject o,int x,int y){
        super(o, o.self.x + o.self.width/2, o.self.y+o.self.height/2, x, y, 15,5,false);
        theta = Math.atan2((o.self.y+o.self.height/2) - y,x - (o.self.x+o.self.width/2));
        damage = 2;
        mobile = true;
        time = 50;
        push = 15;
    }

    @Override
    public void affect(Wall w) {
        push = 0;
        time = (time>15)?15:time;
    }

    @Override
    public void affect(IObject o) {
        if(push!=0)
            o.damage(damage,null);
        o.movedx((int)((push-5)*Math.cos(theta)));
        o.movedy((int)(-(push-5)*Math.sin(theta)));
        o.pIterate();
        time = 0;
    }

    @Override
    public void affect(Block b) {
        push = 0;
        time = (time>15)?15:time;
    }

    @Override
    public boolean isDead() {
        return time < 1 || dead;
    }

    @Override
    public void iterate() {
        time--;
        area.transform(AffineTransform.getTranslateInstance(Math.cos(theta)*push,-Math.sin(theta)*push));
    }
}
