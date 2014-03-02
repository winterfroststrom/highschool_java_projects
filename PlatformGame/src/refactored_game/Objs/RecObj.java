/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package refactored_game.Objs;

import refactored_game.interfaces.Collidable;
import java.awt.geom.Rectangle2D;
import refactored_game.support.Line;
import refactored_game.support.Point;

/**
 *
 * @author Sparky
 */
public class RecObj implements Collidable{
    public double lr;
    public double ud;
    public int speed;
    public Point p;
    public int w;
    public int h;

    public RecObj(Point p, int w, int h){
        this.p = p.getPoint();
        this.w = w;
        this.h = h;
    }

    public RecObj(Point p, int speed, int w, int h){
        this.p = p.getPoint();
        this.speed = speed;
        this.w = w;
        this.h = h;
    }
    @Override
    public void move(){
        p.x += (lr * speed);
        p.y += (ud * speed);
    }

    @Override
    public Line getPath(){
        return new Line(p.getPoint(),falseMove());
    }

    public Point falseMove(){
        return new Point(p.x + (lr * speed),p.y + (ud * speed));
    }

    public void moveUndo(){
        p.x -= (lr * speed);
        p.y -= (ud * speed);
    }

    public void moveTo(double x,double y){
        this.lr = (x - p.x)/Math.abs(y + x - p.x - p.y);
        this.ud = (y - p.y)/Math.abs(y + x - p.x - p.y);
    }

    public double moveToX(double x,double y){
        return (x - p.x)/Math.abs(y + x - p.x - p.y);
    }

    public double moveToY(double x,double y){
        return (y - p.y)/Math.abs(y + x - p.x - p.y);
    }
    public Point getPoint(){
        return new Point(p.x,p.y);
    }
    @Override
    public Rectangle2D.Double construct(){
        return new Rectangle2D.Double(p.x, p.y, w, h);
    }

    public boolean collide(Collidable c){
        return c.construct().intersects(p.x, p.y, w, h) || getPath().intersect(c.getPath());
    }
}
