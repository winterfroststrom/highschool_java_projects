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

public class WindBlow extends Attack{
    int time;
    int push;
    double theta;

    public WindBlow(IObject o,int x,int y){
        super(o, o.self.x + o.self.width/2, o.self.y+o.self.height/2, x, y, o.self.width*3/2,o.self.height*3/2,true);
        theta = Math.atan2((o.self.y+o.self.height/2) - y,x - (o.self.x+o.self.width/2));
        mobile = true;
        time = 20;
        push = 15;
    }

    @Override
    public void affect(Wall w) {
        push /= 2;
    }
    
    @Override
    public void affect(IObject o) {
        if(carry != null)
            switch(carry){
                case fire: o.damage(2,carry);
                    break;
            }
        o.movedx((int)((push-2)*Math.cos(theta)));
        o.movedy((int)(-(push-2)*Math.sin(theta)));
        o.pIterate();
    }
    
    @Override
    public void affect(Block b) {
        b.damage(0, carry);
        if(b.solid)
            push /= 2;
        if(b.canCarry) {
            if(b.isCarry) {
                carry = b.carry;
            } else if(carry != null && b.carry.equals(carry)){
                b.isCarry = true;
                carry = null;
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
