/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package refactored_game.support;

/**
 *
 * @author Sparky
 */
public class Point {
    public double x;
    public double y;
    
    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }
    public Point(Point p){
        this.x = p.x;
        this.y = p.y;
    }
    public Point getPoint(){
        return new Point(this);
    }
    @Override
    public String toString(){
        return "Point[" + x + "," + y + "]";
    }

    public double distance(double x,double y){
        return Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2);
    }    
}
