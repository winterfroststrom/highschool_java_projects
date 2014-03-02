/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package refactored_game.support;

import refactored_game.support.Point;

/**
 *
 * @author Sparky
 */
public class Line {
    public Point p1;
    public Point p2;
    public Line(Point p1, Point p2){
        this.p1 = p1;
        this.p2 = p1;
    }
    public boolean intersect(Line l){
        if(between(p1.x,p2.x,l.p1.x)||between(p1.x,p2.x,l.p2.x)){
            if(between(p1.y,p2.y,l.p1.y)||between(p1.y,p2.y,l.p2.y)){
                return true;
            }
        }
        return false;
    }
    public boolean between(double a, double b, double c){
        return c > Math.min(a, b) && c < Math.max(a, b);
    }
}
