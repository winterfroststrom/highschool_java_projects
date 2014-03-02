/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package refactored_game.Objs.Walls;

import refactored_game.support.Point;
import refactored_game.Objs.Craft;
import java.awt.Graphics2D;
import refactored_game.Buffer;
import refactored_game.support.Line;
import refactored_game.Main;
import refactored_game.Objs.Craft;

/**
 *
 * @author Sparky
 */
public class Wall extends Craft{
    public Wall(Point p,int w, int h,Main base, int health){
        this(p,0,w,h,base,health,0,0);
    }
    public Wall(Point p,int speed,int w, int h,Main base, int health,double lr,double ud){
        super(p,speed,w,h,base,health);
        collideDamage = 3;
        this.lr = lr;
        this.ud = ud;
    }
    @Override
    public Line getPath(){
        Point p1 = p.getPoint();
        Point p2 = p.getPoint();
        p1.x += this.w/2;
        p2.x -= this.w/2;
        p1.y += this.h/2;
        p2.y -= this.h/2;
        return new Line(p1,p2);
    }
    @Override
    public void draw(Graphics2D g){
        g.drawImage(Buffer.buff[3], (int)p.x-3, (int)p.y -3,
                    (int)p.x + w+3, (int)p.y + h+3,
                    0, 0,Buffer.buff[3].getWidth(null),Buffer.buff[3].getHeight(null),null);
    }
}
