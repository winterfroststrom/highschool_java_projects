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

public class FireBall extends Attack{
    int time;
    int push;
    int damage;
    double theta;

    public FireBall(IObject o,int x,int y){
        super(o, o.self.x + o.self.width/2, o.self.y+o.self.height/2, x, y, 30,20,true);
        theta = Math.atan2((o.self.y+o.self.height/2) - y,x - (o.self.x+o.self.width/2));
        mobile = true;
        damage = 3;
        carry = Element.fire;
        time = 15;
        push = 20;
    }

    @Override
    public void affect(Wall w) {
        time = 0;
    }

    @Override
    public void affect(IObject o) {
        o.damage(damage, carry);
        o.movedx((int)((push/3)*Math.cos(theta)));
        o.movedy((int)(-(push/3)*Math.sin(theta)));
        o.pIterate();
        damage = 0;
        time = 0;
    }

    @Override
    public void affect(Block b) {
        b.damage(damage, carry);
        if(b.solid)
            time = 0;
        if(b.canCarry) {
            if(!b.isCarry&&b.carry.equals(carry)){
                b.isCarry = true;
            }
        }
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
