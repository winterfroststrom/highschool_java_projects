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

public class BloodSalvo extends Attack{
    int time = 15;
    int damage = 2;
    int push1 = 45;
    int push2 = 45;
    int push3 = 45;
    int push4 = 45;
    int pm1 = 5;
    int pm2 = 5;
    int pm3 = 5;
    int pm4 = 5;
    Area a1;
    Area a2;
    Area a3;
    Area a4;
    double theta1;
    double theta2;
    double theta3;
    double theta4;

    public BloodSalvo(IObject o,int x,int y,boolean destruct){
        super(o, false, true);
        a1 = (new Area(new Rectangle(o.self.x - 5,o.self.y + o.self.height/2 - 2 - 30,40,4)));
        a2 = (new Area(new Rectangle(o.self.x - 5,o.self.y + o.self.height/2 - 2 + 30,40,4)));
        a3 = (new Area(new Rectangle(o.self.x - 5,o.self.y + o.self.height/2 - 2 - 60,40,4)));
        a4 = (new Area(new Rectangle(o.self.x - 5,o.self.y + o.self.height/2 - 2 + 60,40,4)));
        area = new Area();
        double theta = Math.atan2(y - (o.self.y + o.self.height/2),x - (o.self.x + o.self.width/2));
        a1.transform(AffineTransform.getRotateInstance(theta,
                o.self.x + o.self.width/2,o.self.y + o.self.height/2));
        a2.transform(AffineTransform.getRotateInstance(theta,
                o.self.x + o.self.width/2,o.self.y + o.self.height/2));
        a3.transform(AffineTransform.getRotateInstance(theta,
                o.self.x + o.self.width/2,o.self.y + o.self.height/2));
        a4.transform(AffineTransform.getRotateInstance(theta,
                o.self.x + o.self.width/2,o.self.y + o.self.height/2));
        theta1 = Math.atan2(y - (a1.getBounds().y + a1.getBounds().height/2),
                x - (a1.getBounds().x + a1.getBounds().width/2));
        theta2 = Math.atan2(y - (a2.getBounds().y + a2.getBounds().height/2),
                x - (a2.getBounds().x + a2.getBounds().width/2));
        theta3 = Math.atan2(y - (a3.getBounds().y + a3.getBounds().height/2),
                x - (a3.getBounds().x + a3.getBounds().width/2));
        theta4 = Math.atan2(y - (a4.getBounds().y + a4.getBounds().height/2),
                x - (a4.getBounds().x + a4.getBounds().width/2));
        a1.transform(AffineTransform.getRotateInstance(theta1 - theta,
                a1.getBounds().x + a1.getBounds().width/2,a1.getBounds().y + a1.getBounds().height/2));
        a2.transform(AffineTransform.getRotateInstance(theta2 - theta,
                a2.getBounds().x + a2.getBounds().width/2,a2.getBounds().y + a2.getBounds().height/2));
        a3.transform(AffineTransform.getRotateInstance(theta3 - theta,
                a3.getBounds().x + a3.getBounds().width/2,a3.getBounds().y + a3.getBounds().height/2));
        a4.transform(AffineTransform.getRotateInstance(theta4 - theta,
                a4.getBounds().x + a4.getBounds().width/2,a4.getBounds().y + a4.getBounds().height/2));
        area.add(a1);
        area.add(a2);
        area.add(a3);
        area.add(a4);
        carry = Element.fire;
    }

    @Override
    public void affect(Wall w) {
        if(time>10)
            return;
        if(a1.intersects(w.self)){
            push1 = 0;
        }
        if(a2.intersects(w.self)){
            push2 = 0;
        }
        if(a3.intersects(w.self)){
            push3 = 0;
        }
        if(a4.intersects(w.self)){
            push4 = 0;
        }
    }
    @Override
    public void affect(IObject o) {
        if(a1.intersects(o.self)){
            o.damage(damage,carry);
            o.movedx((int)(damage*Math.cos(theta1)*pm1));
            o.movedy((int)(damage*Math.sin(theta1)*pm1));
            pm1 /= 2;
            o.pIterate();
        }

        if(a2.intersects(o.self)){
            o.damage(damage,carry);
            o.movedx((int)(damage*Math.cos(theta2)*pm2));
            o.movedy((int)(damage*Math.sin(theta2)*pm2));
            pm2 /= 2;
            o.pIterate();
        }
        if(a3.intersects(o.self)){
            o.damage(damage,carry);
            o.movedx((int)(damage*Math.cos(theta3)*pm3));
            o.movedy((int)(damage*Math.sin(theta3)*pm3));
            pm3 /= 2;
            o.pIterate();
        }
        if(a4.intersects(o.self)){
            o.damage(damage,carry);
            o.movedx((int)(damage*Math.cos(theta4)*pm4));
            o.movedy((int)(damage*Math.sin(theta4)*pm4));
            pm4 /= 2;
            o.pIterate();
        }
    }
    @Override
    public void affect(Block b) {
        b.damage(damage, carry);
    }


    @Override
    public boolean isDead() {
        return time < 1;
    }

    @Override
    public void iterate() {
        time--;
        if(time < 12){
            a1.transform(AffineTransform.getTranslateInstance(push1*Math.cos(theta1), push1*Math.sin(theta1)));
            a2.transform(AffineTransform.getTranslateInstance(push2*Math.cos(theta2), push2*Math.sin(theta2)));
            a3.transform(AffineTransform.getTranslateInstance(push3*Math.cos(theta3), push3*Math.sin(theta3)));
            a4.transform(AffineTransform.getTranslateInstance(push4*Math.cos(theta4), push4*Math.sin(theta4)));
            area.reset();
            area.add(a1);
            area.add(a2);
            area.add(a3);
            area.add(a4);
        }
    }
}
