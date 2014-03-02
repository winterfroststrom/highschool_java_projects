/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package quest;

/**
 *
 * @author Scalene
 */
import java.awt.geom.Area;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

public class BloodCross extends Attack{
    int time = 15;
    int damage = 4;
    double theta;
    boolean destruct;

    public BloodCross(IObject o,int x,int y,boolean destruct){
        super(o, true, true);
        this.destruct = destruct;
        Area a1 = (new Area(new Rectangle(o.self.x,
                o.self.y + o.self.height/2 - 3 - 10,35,6)));
        Area a2 = (new Area(new Rectangle(o.self.x,
                o.self.y + o.self.height/2 - 3 + 10,35,6)));
        area = new Area();
        area.add(a1.createTransformedArea(AffineTransform.getRotateInstance(Math.PI/3
                ,o.self.x + o.self.width/2,o.self.y + o.self.height/2)));
        area.add(a2.createTransformedArea(AffineTransform.getRotateInstance(-Math.PI/3,
                        o.self.x + o.self.width/2,o.self.y + o.self.height/2)));
        theta = Math.atan2(y - (o.self.y + o.self.height/2),x - (o.self.x + o.self.width/2));
        area.transform(AffineTransform.getRotateInstance(theta
                //x - (o.self.x + o.self.width/2),y - (o.self.y + o.self.height/2)
                                ,o.self.x + o.self.width/2,
                                o.self.y + o.self.height/2));
        carry = Element.fire;
    }

    @Override
    public void affect(Wall w) {}
    @Override
    public void affect(IObject o) {
        if(time > 1 && time < 15){
            o.damage(damage,carry);
            o.movedx((int)(damage*Math.cos(theta)*5));
            o.movedy((int)(damage*Math.sin(theta)*5));
            o.pIterate();
        }
    }
    @Override
    public void affect(Block b) {
        b.damage(damage, carry);
        if(destruct)
            b.dead = true;
    }


    @Override
    public boolean isDead() {
        return time < 1;
    }

    @Override
    public void iterate() {
        time--;
    }
}
