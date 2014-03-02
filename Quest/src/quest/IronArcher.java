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
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.awt.geom.AffineTransform;

public class IronArcher extends NPC{
    Area sight;
    double preTheta;
    double theta;
    IObject target;

    public IronArcher(Game game, int x, int y,int areaid,int id) {
        super(game,x,y,50,50,0,10,areaid,id);
        sight = (new Area(new Rectangle(x + 25,y - 5,350,60))).createTransformedArea(
                    AffineTransform.getRotateInstance(
                            xdir, ydir, x + self.width/2, y + self.height/2));
    }

    public void sense(){
        if(target != null)
            theta = Math.atan2(ydir, xdir);
        sight.transform(AffineTransform.getRotateInstance(theta - preTheta,  self.x + self.width/2, self.y + self.height/2));
        preTheta = theta;
        target = (sight.intersects(game.player.self))?game.player:null;
    }

    @Override
    public void attack() {
        if(target != null){
            atk = new Arrow(this,target.self.x + target.self.width/2,target.self.y + target.self.height/2);
            game.atks.add(atk);
        }
    }

    @Override
    public void think() {
        if(target == null){
            theta += Math.PI/48;
            theta %= (Math.PI*2);
        } else {
            attack = true;
            double angle = Math.atan2((game.player.self.y+game.player.self.height/2)
                    - (self.y+self.height/2),(game.player.self.x+game.player.self.width/2)
                    - (self.x+self.width/2));
            xdir = Math.cos(angle);
            ydir = Math.sin(angle);
        }
    }

    @Override
    public void paintSelf(Graphics2D g) {
        super.paintSelf(g);
        g.setColor(new Color(255,255,0,100));
        g.fill(sight);
    }

        @Override
    public void movedx(int deltax) {}

    @Override
    public void movedy(int deltay) {}

    @Override
    public void damage(int damage, Element e) {
        if(e!=null&&e.equals(Element.fire))
            super.damage(2*damage, e);
        else
            super.damage(damage, e);
    }
}
