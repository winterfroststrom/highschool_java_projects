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

public class Jar extends Block{

    public Jar(Game game,int x,int y,int width,int height,boolean mobile,boolean solid){
        super(game,x,y,width,height,mobile,solid);
    }

    @Override
    public void die() {
        (new Item(game,self.x+self.width/2-5,self.y+self.height/2-5)).add();
    }

    @Override
    public void paintSelf(Graphics2D g) {
        g.setColor(new Color(200,100,0,100));
        g.fill(self);
    }

    @Override
    public void damage(int damage, Element e) {
        if(solid || damage > 0)
            dead = true;
    }
}
