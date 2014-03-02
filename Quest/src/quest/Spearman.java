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

public class Spearman extends NPC{
    Area sight;
    Area avoidence;
    double preTheta;
    int movetime;
    IObject target;

    public Spearman(Game game,int x,int y,int areaid,int id) {
        super(game,x,y,30,30,5,3,areaid,id);
        sight = (new Area(new Rectangle(x + 15,
                    y - 5,150,40))).createTransformedArea(
                    AffineTransform.getRotateInstance(
                            xdir, ydir, x + self.width/2, y + self.height/2));
        avoidence = (new Area(new Rectangle(x + 15,
                    y - 5,35,40))).createTransformedArea(
                    AffineTransform.getRotateInstance(
                            xdir, ydir, x + self.width/2, y + self.height/2));
    }

    public void sense(){
        double theta = Math.atan2(ydir, xdir);
        sight.transform(AffineTransform.getRotateInstance(theta - preTheta,  self.x + self.width/2, self.y + self.height/2));
        avoidence.transform(AffineTransform.getRotateInstance(theta - preTheta,  self.x + self.width/2, self.y + self.height/2));
        preTheta = theta;
        target = (sight.intersects(game.player.self))?game.player:null;
        if(target == null){
            for(Wall w: game.walls)
                if(avoidence.intersects(w.self)){
                    movetime = 0;
                    break;
                }
        }
        for(Floor f:game.floor)
            if(avoidence.intersects(f.self)){
                target = null;
                xdir *= -1;
                ydir *= -1;
                break;
            }
    }

    @Override
    public void attack() {
        if(target != null){
            atk = new SpearJab(this,target.self.x + target.self.width/2,target.self.y + target.self.height/2);
            game.atks.add(atk);
        }
    }

    @Override
    public void think() {
//        if(self.x - game.player.self.x == 0){
//            xdir = 0;
//            ydir = (self.y - game.player.self.y<0)?1:-1;
//            xdir = (self.x - game.player.self.x<0)?1:-1;
//            return;
//        }
//        double angle = Math.atan((double)(self.y - game.player.self.y)/(self.x - game.player.self.x));
//        xdir = Math.abs(Math.cos(angle))*((self.x - game.player.self.x<0)?1:-1);
//        ydir = Math.abs(Math.sin(angle))*((self.y - game.player.self.y<0)?1:-1);
        if(target == null){
        if(movetime<1){
            movetime = (int)(Math.random()*3)*7;
            if(Math.random()>.5){
                xdir = ((Math.random()>.5)?-1:1);
                ydir = 0;
            } else {
                ydir = ((Math.random()>.5)?-1:1);
                xdir = 0;
            }
        }

        /*
         if(xtime<3){
            xtime = (int)(Math.random()*3)*7 + 10;
            if(Math.random()>.5){
                xdir = ((Math.random()>.5)?-1:1);
                ydir = 0;
            } else {
                ydir = ((Math.random()>.5)?-1:1);
                xdir = 0;
            }
        } else if(xtime < 10){
            xdir = 0;
            ydir = 0;
        }
         */
        //if(ytime<1){
        //    ytime = (int)(Math.random()*20);
        //    ydir = Math.random()*((Math.random()>.5)?-1:1);
        //}
        movetime--;
        } else {
        //ytime--;
            if(Math.pow(game.player.self.x - self.x,2) + Math.pow(game.player.self.y - self.y, 2) < 1600)
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
        g.setColor(new Color(255,255,0,100));
        g.fill(avoidence);
    }

    @Override
    public void movedx(int deltax) {
        super.movedx(deltax);
        sight.transform(AffineTransform.getTranslateInstance(deltax,0));
        avoidence.transform(AffineTransform.getTranslateInstance(deltax,0));
    }

    @Override
    public void movedy(int deltay) {
        super.movedy(deltay);
        sight.transform(AffineTransform.getTranslateInstance(0,deltay));
        avoidence.transform(AffineTransform.getTranslateInstance(0,deltay));
    }
}
