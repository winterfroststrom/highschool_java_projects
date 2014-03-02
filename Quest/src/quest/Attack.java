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
import java.awt.geom.Area;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public abstract class Attack {
    Object owner;
    int shape;
    Element carry;
    boolean dead = false;
    boolean mobile;
    boolean affectNPC;
    private static final int rect = 1;
    private static final int cir = 2;
    private static final int ccir = 3;
    private static final int custom = 4;
    Area area;
    int x;
    int y;
    int r;
    int r2;
    int d2;
    int d2in;

    public Attack(Object o,boolean affectNPC,boolean mobile) {
        owner = o;
        shape = custom;
        this.affectNPC = affectNPC;
        this.mobile = mobile;
    }

    public Attack(Object o, int x, int y, int r, int r2,boolean affectNPC) {
        owner = o;
        shape = ccir;
        this.affectNPC = affectNPC;
        this.x = x;
        this.y = y;
        this.r = r;
        this.r2 = r2;
        this.d2 = r;
        this.d2in = r2*r2;
        area = new Area(new Ellipse2D.Double(x - r/2 + o.self.width/2, y - r/2 + o.self.height/2, r, r));
        area.subtract(new Area(new Ellipse2D.Double(x - r2/2 + o.self.width/2, y - r2/2 + o.self.height/2, r2, r2)));
    }

    public Attack(Object o, int x, int y, int r, boolean affectNPC) {
        owner = o;
        this.affectNPC = affectNPC;
        shape = cir;
        this.x = x;
        this.y = y;
        this.r = r;
        this.d2 = r*r;
        area = new Area(new Ellipse2D.Double(x - r/2 + o.self.width/2, y - r/2 + o.self.height/2, r, r));
    }

    public Attack(Object o, int x1, int y1, int x2, int y2, int w,int h,boolean affectNPC) {
        this.affectNPC = affectNPC;
        owner = o;
        this.shape = rect;
        area = (new Area(new Rectangle(x1,
                y1 - h/2,w,h))).createTransformedArea(
                AffineTransform.getRotateInstance(x2 - x1, y2 - y1,x1,y1));
    }

    public abstract boolean isDead();

    public abstract void iterate();

    public abstract void affect(IObject o);
    public abstract void affect(Wall w);
    public abstract void affect(Block b);

    public boolean intersect(int x,int y,int w,int h){
        return area.intersects(x,y,w,h);
    }

    public boolean intersect(Object o){
        if(shape == rect)
            return area.intersects(o.self);
        int dx = o.self.x - x;int dy = o.self.y - y;
        switch(shape){
            case cir:  return ((dx)*(dx) + (dy)*(dy) < d2);
            case ccir: int odd = (dx)*(dx) + (dy)*(dy);
                return (odd < d2 && odd > d2in);
        }
        if(shape == custom)
            return area.intersects(o.self);
        return false;
    }

    public void paintSelf(Graphics2D g){
        if(carry != null)
            switch(carry){
                case fire: g.setColor(Color.RED);
                    break;
            }
        else
            g.setColor(Color.PINK);
        g.fill(area);
    }
}
